package com.tss.prizebond.data

import com.tss.prizebond.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)

class AuthViewModel(
    private val repo: AuthRepository
) {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _loginState = MutableStateFlow(AuthUiState())
    val loginState: StateFlow<AuthUiState> = _loginState

    private val _registerState = MutableStateFlow(AuthUiState())
    val registerState: StateFlow<AuthUiState> = _registerState

    fun login(email: String, password: String) {
        _loginState.value = AuthUiState(isLoading = true)
        scope.launch {
            try {
                val user = repo.login(email, password)
                _loginState.value = AuthUiState(user = user)
            } catch (t: Throwable) {
                _loginState.value = AuthUiState(error = t.message ?: "Login failed")
            }
        }
    }

    fun register(name: String, email: String, password: String, confirm: String) {
        _registerState.value = AuthUiState(isLoading = true)
        scope.launch {
            try {
                val user = repo.register(name, email, password, confirm)
                _registerState.value = AuthUiState(user = user)
            } catch (t: Throwable) {
                _registerState.value = AuthUiState(error = t.message ?: "Register failed")
            }
        }
    }
}