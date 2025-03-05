package com.jsm.cashierone.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(val token: String)
