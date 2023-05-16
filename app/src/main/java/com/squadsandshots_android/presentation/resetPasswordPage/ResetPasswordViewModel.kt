package com.squadsandshots_android.presentation.resetPasswordPage

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squadsandshots_android.useCases.ConfirmPasswordResetUseCase
import com.squadsandshots_android.useCases.RequestResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: RequestResetPasswordUseCase,
    private val confirmPasswordReset: ConfirmPasswordResetUseCase
): ViewModel() {

    private val _events: MutableStateFlow<ResetPasswordViewEvent?> = MutableStateFlow(null)
    val events: StateFlow<ResetPasswordViewEvent?> get() = _events.asStateFlow()

    fun sendConfirmCode(code: String) {
        confirmPasswordReset.invoke(viewModelScope, code) { result ->
            result.result(
                onSuccess = {
                    _events.value = ResetPasswordViewEvent.ConfirmSuccess
                    resetStateAfterDelay()
                },
                onFailure = {
                    _events.value = ResetPasswordViewEvent.ConfirmFailed(it.message ?: "Confirm failed")
                    resetStateAfterDelay()
                }
            )
        }
    }

    fun validateEmailAndResetPassword(email: String) {
        if(emailValid(email)) {
            resetPassword(email)
        } else {
            _events.value = ResetPasswordViewEvent.RequestFailed("Invalid email")
            resetStateAfterDelay()
        }
    }

    private fun resetPassword(email: String) {
        resetPasswordUseCase.invoke(viewModelScope, email) { result ->
            result.result(
                onSuccess = {
                    it.addOnSuccessListener {
                        _events.value = ResetPasswordViewEvent.RequestSuccess
                        resetStateAfterDelay()
                    }
                    it.addOnFailureListener { exception ->
                        _events.value = ResetPasswordViewEvent.RequestFailed(exception.message ?: "Reset failed")
                        resetStateAfterDelay()
                    }
                },
                onFailure = {
                    _events.value = ResetPasswordViewEvent.RequestFailed(it.message ?: "Reset failed")
                    resetStateAfterDelay()
                }
            )
        }
    }

    private fun emailValid(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    private fun resetStateAfterDelay() {
        viewModelScope.launch {
            delay(500)
            _events.value = null
        }
    }

    sealed class ResetPasswordViewEvent {
        object RequestSuccess : ResetPasswordViewEvent()
        object ConfirmSuccess : ResetPasswordViewEvent()
        data class RequestFailed(val message: String) : ResetPasswordViewEvent()
        data class ConfirmFailed(val message: String) : ResetPasswordViewEvent()
    }
}