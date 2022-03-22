package com.squadsandshots_android.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.squadsandshots_android.requestModels.LoginRequest
import kotlin.math.sign


@Composable
fun SignUpPage(signUpViewModel: SignUpViewModel = viewModel()) {
    val loginRequest by rememberSaveable { mutableStateOf(LoginRequest()) }
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        SquadsAndShotsTitle()
        UsernameAndPasswordFields(loginRequest)
        SignUpButton(onClick = {
            signUpViewModel.createAccount(loginRequest)
        })

    }
}
