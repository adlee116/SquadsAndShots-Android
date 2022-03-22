package com.squadsandshots_android.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squadsandshots_android.requestModels.LoginRequest
import com.squadsandshots_android.useCases.CreateFireBaseUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val createFireBaseUserUseCase: CreateFireBaseUserUseCase) : ViewModel() {

    private val _events: MutableStateFlow<LoginEvent?> = MutableStateFlow(null)
    val events: StateFlow<LoginEvent?> get() = _events.asStateFlow()

    fun createAccount(loginRequest: LoginRequest) {
        createFireBaseUserUseCase.invoke(viewModelScope, loginRequest, onSuccess = {}, onFailure = {})
    }

    data class LoginEvent(val state: LoginViewEvent)
    sealed class LoginViewEvent {
        object SignUpFailed : LoginViewEvent()
        object SignUpSuccess : LoginViewEvent()
    }
}