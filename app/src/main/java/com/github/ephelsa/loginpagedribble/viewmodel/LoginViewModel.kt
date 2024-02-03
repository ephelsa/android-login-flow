package com.github.ephelsa.loginpagedribble.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ephelsa.loginpagedribble.repository.AuthUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authUserRepository: AuthUserRepository
): ViewModel() {

    private val emailChange = MutableStateFlow<String?>(null)
    val onEmailChange: StateFlow<String?> = emailChange

    private val passwordChange = MutableStateFlow<String?>(null)
    val onPasswordChange: StateFlow<String?> = passwordChange

    private val isLoginDisable = MutableStateFlow<Boolean>(false)
    val onLoginButtonDisabled: StateFlow<Boolean> = isLoginDisable

    private val authState = MutableStateFlow<AuthState>(AuthState.Init)
    val onAuthState: StateFlow<AuthState> = authState

    fun onEmailTextChange(email: String) {
        emailChange.value = email
    }

    fun onPasswordTextChange(password: String) {
        passwordChange.value = password
    }

    fun onLoginClick() =  viewModelScope.launch {
        isLoginDisable.value = !isLoginDisable.value
        val result = authUserRepository.loginUser(onEmailChange.value ?: "", onPasswordChange.value ?: "")
        authState.value = if (result.isSuccess) {
            AuthState.Success
        } else {
            AuthState.Failure
        }

        isLoginDisable.value = !isLoginDisable.value
    }

    enum class AuthState {
        Init,
        Success,
        Failure
    }
}