package com.jsm.cashierone.features.login

sealed class LoginEvent {
    object OnLoginClick : LoginEvent()
    data class OnEmailChange(val email: String) : LoginEvent()
    data class OnPasswordChange(val password: String) : LoginEvent()
}