package com.squadsandshots_android.presentation.resetPasswordPage

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(): ViewModel() {

    fun resetPassword() {

    }

    private fun emailValid(email: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}