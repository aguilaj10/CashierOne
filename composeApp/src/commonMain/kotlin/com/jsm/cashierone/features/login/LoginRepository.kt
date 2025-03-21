package com.jsm.cashierone.features.login

import com.jsm.cashierone.model.request.LoginRequest
import com.jsm.cashierone.model.response.LoginResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.postApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

interface LoginRepository {
    suspend fun login(email: String, password: String): ApiResponse<LoginResponse>
}

class LoginRepositoryImpl(
    private val client: HttpClient
) : LoginRepository {
    override suspend fun login(email: String, password: String): ApiResponse<LoginResponse> {
        return client.postApiResponse<LoginResponse>("/login") {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody(LoginRequest(email, password))
        }
    }
}