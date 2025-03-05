package com.jsm.cashierone.model.response

import kotlinx.serialization.Serializable

@Serializable
data class OneResponse<T>(
    val message: String,
    val data: T? = null,
    val status: Int = 200,
)
