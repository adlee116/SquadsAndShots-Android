package com.squadsandshots_android.requestModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    var username: String = "",
    var password: String = ""
): Parcelable {
    val usernameAndPassword: Boolean get() = username.isNotEmpty() && password.isNotEmpty()
}