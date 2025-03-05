package com.jsm.cashierone.features.login

sealed class LoginState {
    data class Loading(val message: String) : LoginState()
    data class Error(val message: String, val email: String, val password: String) : LoginState()
    data class Success(val message: String, val email: String, val password: String) : LoginState()
}

internal fun LoginState.getEmail(): String {
    return when (this) {
        is LoginState.Error -> email
        is LoginState.Success -> email
        else -> ""
    }
}

internal fun LoginState.getPassword(): String {
    return when (this) {
        is LoginState.Error -> password
        is LoginState.Success -> password
        else -> ""
    }
}
