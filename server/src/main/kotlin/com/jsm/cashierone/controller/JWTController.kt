package com.jsm.cashierone.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.jsm.cashierone.model.User
import com.jsm.cashierone.repository.user.UserRepository
import com.jsm.cashierone.security.JWTConfig
import java.time.Clock

class JWTController(
    private val userRepository: UserRepository
) {
    fun createToken(jwtConfig: JWTConfig, clock: Clock, user: User, expirationSeconds: Long): String =
        JWT.create()
            .withAudience(jwtConfig.audience)
            .withIssuer(jwtConfig.issuer)
            .withClaim("name", user.email)
            .withClaim("role", user.rolId)
            .withExpiresAt(clock.instant().plusSeconds(expirationSeconds))
            .sign(Algorithm.HMAC256(jwtConfig.secret))

    suspend fun verify(jwtConfig: JWTConfig, token: String, userRepository: UserRepository): User? =
        try {
            val jwt = JWT.require(Algorithm.HMAC256(jwtConfig.secret))
                .withAudience(jwtConfig.audience)
                .withIssuer(jwtConfig.issuer)
                .build()
                .verify(token)
            jwt.getClaim("email").asString()?.let { email ->
                userRepository.getByEmail(email)
            }
        } catch (e: JWTVerificationException) {
            null
        }
}