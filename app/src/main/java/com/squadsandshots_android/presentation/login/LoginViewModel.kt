package com.squadsandshots_android.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squadsandshots_android.requestModels.LoginRequest
import com.squadsandshots_android.useCases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
//    private val getCurrentFireBaseUser: GetCurrentFireBaseUserUseCase
) : ViewModel() {

//    private val _events: MutableStateFlow<LoginEvent?> = MutableStateFlow(null)
//    val events: StateFlow<LoginEvent?> get() = _events.asStateFlow()

//    fun checkIfUserAlreadyAuthorised() {
//        getCurrentFireBaseUser.invoke(viewModelScope) { result ->
//            result.result(
//                onSuccess = {},
//                onFailure = {}
//            )
//        }
//    }

    fun login(loginRequest: LoginRequest): Int {
//        loginUseCase.invoke(viewModelScope, loginRequest, onSuccess = {}, onFailure = {})
        return 1
    }

//    private fun updateUI(loginSuccess: LoginViewEvent) {
//        _events.value = LoginEvent(loginSuccess)
//    }
//
//    data class LoginEvent(val state: LoginViewEvent)
//    sealed class LoginViewEvent {
//        object LoginFailed : LoginViewEvent()
//        object LoginSuccess : LoginViewEvent()
//    }
}