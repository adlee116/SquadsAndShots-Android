package com.squadsandshots_android.presentation.login

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.squadsandshots_android.R.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.squadsandshots_android.presentation.destinations.HomePageDestination
import com.squadsandshots_android.presentation.destinations.SignUpPageDestination
import com.squadsandshots_android.requestModels.LoginRequest
import kotlinx.coroutines.flow.collect

@Destination(start = true)
@Composable
fun LoginPage(navController: DestinationsNavigator, viewModel: LoginViewModel = hiltViewModel()) {
    val loginRequest by rememberSaveable { mutableStateOf(LoginRequest()) }
    SetupViewModelListener(viewModel = viewModel, navController = navController)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        LoginCenterContent(
            loginRequest = loginRequest,
            viewModel = viewModel,
            navigator = navController
        )
//        ResetPasswordButton(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
//            navController.navigate(ResetPassWordPageDestination())
//        }
    }
}

@Composable
fun SetupViewModelListener(viewModel: LoginViewModel, navController: DestinationsNavigator) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    viewModel.checkIfUserAlreadyAuthorised()
    lifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.events.collect {
            when (it) {
                is LoginViewModel.LoginViewEvent.CredentialsInvalid -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is LoginViewModel.LoginViewEvent.LoginFailed -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                LoginViewModel.LoginViewEvent.LoginSuccess -> {
                    navController.navigate(HomePageDestination())
                }
                null -> {}
            }

        }
    }
}

@Composable
fun LoginCenterContent(
    loginRequest: LoginRequest,
    viewModel: LoginViewModel,
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        SquadsAndShotsTitle()
        UsernameAndPasswordFields(loginRequest)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = SpaceEvenly
        ) {
            LoginButton(onClick = { viewModel.validateFieldsAndLogin(it) }, loginRequest)
            SignUpButton(onClick = { navigator.navigate(SignUpPageDestination()) })
        }

    }
}

@Composable
fun UsernameAndPasswordFields(loginRequest: LoginRequest) {
    Column(modifier = Modifier.wrapContentSize()) {
        TextFieldWithHint(stringResource(string.email), false) { loginRequest.username = it }
        TextFieldWithHint(stringResource(string.password), true) { loginRequest.password = it }
    }
}

@Composable
fun LoginButton(onClick: (LoginRequest) -> Unit, loginRequest: LoginRequest) {
    ClickableText(
        text = AnnotatedString("Login"),
        onClick = { onClick(loginRequest) },
        style = TextStyle(
            color = Blue,
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun SignUpButton(onClick: () -> Unit) {
    ClickableText(
        text = AnnotatedString("Sign up"),
        onClick = { onClick() },
        style = TextStyle(
            color = Blue,
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun SquadsAndShotsTitle() {
    Text(
        text = "Squads&Shots",
        fontSize = 42.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                val brush = Brush.horizontalGradient(listOf(Color.Red, Blue))
                onDrawWithContent {
                    drawContent()
                    drawRect(brush, blendMode = BlendMode.SrcAtop)
                }
            }
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun TextFieldWithHint(labelText: String, password: Boolean, onValueChanged: (String) -> Unit) {
    var stringValue by rememberSaveable { mutableStateOf("") }
    TextField(
        value = stringValue,
        onValueChange = {
            stringValue = it
            onValueChanged(it)
        },
        maxLines = 1,
        label = { Text(labelText) },
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (password) KeyboardType.Password else KeyboardType.Email
        ),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .border(
                width = 3.dp,
                brush = Brush.horizontalGradient(
                    listOf(
                        Color.Red,
                        Blue
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        )
    )
}

