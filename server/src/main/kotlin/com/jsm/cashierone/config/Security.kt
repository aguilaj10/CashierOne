package com.jsm.cashierone.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.jsm.cashierone.security.JWTConfig
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.auth.authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import java.time.Clock

fun Application.configureSecurity(jwtConfig: JWTConfig) {
    authentication {
        jwt("auth-jwt") {
            realm = jwtConfig.realm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtConfig.secret))
                    .withAudience(jwtConfig.audience)
                    .withIssuer(jwtConfig.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtConfig.audience)) JWTPrincipal(credential.payload) else null
            }
            challenge { scheme, realm ->
                call.respond(
                    HttpStatusCode.Unauthorized,
                    "Token to access ${HttpHeaders.WWWAuthenticate} $scheme realm=\"$realm\" is either invalid or expired."
                )
            }
        }
    }
}

fun JWTPrincipal.rolId(): String? = this.payload.getClaim("role").asString()
fun JWTPrincipal.email(): String? = this.payload.getClaim("email").asString()
fun JWTPrincipal.ttl(clock: Clock): Long? = this.payload.expiresAt?.time?.minus(clock.millis())