package com.squadsandshots_android.presentation.resetPasswordPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.squadsandshots_android.presentation.login.TextFieldWithHint

@Destination
@Composable
fun ResetPassWordPage(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldWithHint(
                labelText = "example@example.com",
                password = false,
                onValueChanged = {})
            ResetPasswordPageButton("Send reset password request") {}
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldWithHint(labelText = "12334556664", password = false, onValueChanged = {})
            ResetPasswordPageButton("Send confirm code") {}
        }

    }
}

@Composable
fun ResetPasswordPageButton(text: String, onClick: () -> Unit) {
    Button(onClick = {}) {
        Text(text = text)
    }
}