package com.jsm.cashierone.model.response

import com.jsm.cashierone.model.User
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val refreshToken: String,
    val user: User,
)
