package com.jsm.cashierone.config

import com.jsm.cashierone.config.di.ModulesConfig
import com.jsm.cashierone.model.request.LoginRequest
import com.jsm.cashierone.model.request.RefreshToken
import com.jsm.cashierone.model.response.LoginResponse
import com.jsm.cashierone.repository.user.UserRepository
import com.jsm.cashierone.security.JWTConfig
import com.jsm.cashierone.security.createToken
import com.jsm.cashierone.security.verify
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.resources.Resources
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.kodein.di.instance
import java.time.Clock

fun Application.configureRouting(jwtConfig: JWTConfig) {
    val userRepository by ModulesConfig.di.instance<UserRepository>()
    val clock by ModulesConfig.di.instance<Clock>()

    install(Resources)

    routing {
        post("/login") {
            val login = call.receive<LoginRequest>()
            try {
                val user = userRepository.login(login.email, login.password)

                fun createToken(expirationSeconds: Long): String =
                    jwtConfig.createToken(clock, user, expirationSeconds)

                val accessToken = createToken(jwtConfig.expirationSeconds.accessToken)
                val refreshToken = createToken(jwtConfig.expirationSeconds.refreshToken)
                call.respond(LoginResponse(accessToken, refreshToken, user))

            } catch (ex: EntityNotFoundException) {
                call.respond(HttpStatusCode.Forbidden, "Login failed")
                return@post
            }
        }
        post("/refresh") {
            // Extract the refresh token from the request
            val refreshToken = call.receive<RefreshToken>()

            // Verify the refresh token and obtain the user
            val user = jwtConfig.verify(refreshToken.token, userRepository) ?: run {
                call.respond(HttpStatusCode.Forbidden, "Invalid refresh token")
                return@post
            }

            // Create new access and refresh tokens for the user
            val newAccessToken = jwtConfig.createToken(
                clock,
                user,
                jwtConfig.expirationSeconds.accessToken
            )
            val newRefreshToken = jwtConfig.createToken(
                clock,
                user,
                jwtConfig.expirationSeconds.refreshToken
            )

            // Respond with the new tokens
            call.respond(LoginResponse(newAccessToken, newRefreshToken, user))
        }
        authenticate("auth-jwt") {
            get("/me") {
                val principal = call.principal<JWTPrincipal>()
                call.respondText("Hello ${principal?.email()}! Your token expires in ${principal?.ttl(clock)} ms.")
            }
            get("/admin") {
                val principal = call.principal<JWTPrincipal>()
                if (principal?.rolId() != "1") {
                    call.respond(HttpStatusCode.Forbidden, "You are not authorized to access this resource!")
                    return@get
                }
                call.respondText("Hello admin ${principal.email()}! Your token expires in ${principal.ttl(clock)} ms.")
            }
        }
    }
}