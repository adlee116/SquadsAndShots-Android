package com.squadsandshots_android.presentation.homePage

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.squadsandshots_android.presentation.login.TextFieldWithHint

@Preview
@Destination
@Composable
fun HomePage() {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        var yourName by rememberSaveable { mutableStateOf("") }
        TextFieldWithHint(labelText = yourName, password = false, onValueChanged = { yourName = it })
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            HomePageButton("Create game") {}
            HomePageButton("Join game") {}
        }
    }
}

@Composable
fun HomePageButton(buttonText: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(buttonText, textAlign = TextAlign.Center)
    }
}