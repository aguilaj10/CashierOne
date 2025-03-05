package com.jsm.cashierone.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.jsm.cashierone.model.User
import com.jsm.cashierone.repository.user.UserRepository
import java.time.Clock

data class JWTConfig(
    val realm: String,
    val secret: String,
    val audience: String,
    val issuer: String,
    val expirationSeconds: ExpirationSecondsConfig
) {
    data class ExpirationSecondsConfig(val accessToken: Long, val refreshToken: Long)
}

fun JWTConfig.createToken(clock: Clock, user: User, expirationSeconds: Long): String =
    JWT.create()
        .withAudience(this.audience)
        .withIssuer(this.issuer)
        .withClaim("email", user.email)
        .withClaim("rolId", user.rolId)
        .withExpiresAt(clock.instant().plusSeconds(expirationSeconds))
        .sign(Algorithm.HMAC256(this.secret))

suspend fun JWTConfig.verify(token: String, userRepository: UserRepository): User? =
    try {
        val jwt = JWT.require(Algorithm.HMAC256(this.secret))
            .withAudience(this.audience)
            .withIssuer(this.issuer)
            .build()
            .verify(token)
        jwt.getClaim("email").asString()?.let { email ->
            userRepository.getByEmail(email)
        }
    } catch (e: JWTVerificationException) {
        null
    }