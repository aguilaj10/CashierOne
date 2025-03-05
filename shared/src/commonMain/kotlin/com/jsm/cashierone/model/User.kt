package com.jsm.cashierone.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String,
    val phoneNumber: String?,
    val name: String,
    val rolId: Int?,
)
