package com.squadsandshots_android.presentation.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.squadsandshots_android.R.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.squadsandshots_android.presentation.navigation.Screen
import com.squadsandshots_android.requestModels.LoginRequest

@Composable
fun LoginPage(loginViewModel: LoginViewModel, navController: NavHostController) {
    val loginRequest by rememberSaveable { mutableStateOf(LoginRequest()) }
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        SquadsAndShotsTitle()
        UsernameAndPasswordFields(loginRequest)
        LoginButton(onClick = { loginViewModel.login(it) }, loginRequest)
        SignUpButton(onClick = { navController.navigate(Screen.Create.route) })
//        Row {
//            LoginButton(onClick = { loginViewModel.login(it) }, loginRequest)
//            val navController = LocalNavController.current
//            SignUpButton(onClick = { navController.navigate(Screen.Create.route) })
//        }

    }
}

@Composable
fun UsernameAndPasswordFields(loginRequest: LoginRequest) {
    Column(modifier = Modifier.wrapContentSize()) {
        TextFieldWithHint(stringResource(string.username), false) { loginRequest.username = it }
        TextFieldWithHint(stringResource(string.password), true) { loginRequest.password = it }
    }
}

@Composable
fun LoginButton(onClick:(LoginRequest) -> Unit, loginRequest: LoginRequest) {
    ClickableText(
        text = AnnotatedString("Login"),
        onClick = { onClick(loginRequest) },
        style = TextStyle(
            color = Blue,
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SignUpButton(onClick:() -> Unit) {
    ClickableText(
        text = AnnotatedString("Sign up"),
        onClick = { onClick() },
        style = TextStyle(
            color = Blue,
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.fillMaxWidth()
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
    var stringValue by rememberSaveable { mutableStateOf("")}
    TextField(
        value = stringValue,
        onValueChange = {
            stringValue = it
            onValueChanged(it)
                        },
        label = { Text(labelText) },
        visualTransformation = if(password) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if(password) KeyboardType.Password else KeyboardType.Email
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
