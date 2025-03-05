package com.jsm.cashierone.config

import com.jsm.cashierone.security.JWTConfig
import io.ktor.server.config.ApplicationConfig

fun ApplicationConfig.jwtConfig(): JWTConfig {
    return JWTConfig(
        realm = property("realm").getString(),
        secret = property("secret").getString(),
        audience = property("audience").getString(),
        issuer = property("issuer").getString(),
        expirationSeconds = JWTConfig.ExpirationSecondsConfig(
            accessToken = property("expirationSeconds.accessToken").getString().toLong(),
            refreshToken = property("expirationSeconds.refreshToken").getString().toLong()
        )
    )
}