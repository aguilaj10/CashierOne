package com.jsm.cashierone

import com.jsm.cashierone.config.configureDatabases
import com.jsm.cashierone.config.configureRouting
import com.jsm.cashierone.config.configureSecurity
import com.jsm.cashierone.config.jwtConfig
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    val jwtConfig = environment.config.config("ktor.auth.jwt").jwtConfig()
    configureDatabases()
    configureSecurity(jwtConfig)
    configureRouting(jwtConfig)
}