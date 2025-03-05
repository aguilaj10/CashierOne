package com.jsm.cashierone.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json(
            Json {
                encodeDefaults = true
                isLenient = true
                coerceInputValues = true
                ignoreUnknownKeys = true
            }
        )
    }

    defaultRequest {
        host = "localhost"
        port = 8080
    }
}