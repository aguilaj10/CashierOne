package com.jsm.cashierone.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsm.cashierone.utils.sha2Hash
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginState>(LoginState.Success("", "", ""))
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLoginClick -> onLoginClick()

            is LoginEvent.OnEmailChange -> onEmailChange(event)
            is LoginEvent.OnPasswordChange -> onPasswordChange(event)
        }
    }

    private fun onPasswordChange(event: LoginEvent.OnPasswordChange) {
        _uiState.update {
            LoginState.Success("Success", it.getEmail(), event.password)
        }
    }

    private fun onEmailChange(event: LoginEvent.OnEmailChange) {
        _uiState.update {
            LoginState.Success("Success", event.email, it.getPassword())
        }
    }

    private fun onLoginClick() {
        println("${uiState.value.getEmail()} -- ${uiState.value.getPassword()}")
        viewModelScope.launch {
            loginRepository.login(uiState.value.getEmail(), uiState.value.getPassword().sha2Hash())
                .onSuccess {}
                .onError {
                    _uiState.update {
                        LoginState.Error(this.message(), uiState.value.getEmail(), "")
                    }
                }
        }
    }
}