package com.example.squadsandshots_android.presentation.reusableComponents

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.squadsandshots_android.R.*

@Composable
fun LoginPage(loginClicked: (Int) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        SquadsAndShotsTitle()
        UsernameAndPasswordFields()
        loginButton(onClick = loginClicked)
    }
}

@Composable
fun UsernameAndPasswordFields() {
    Column(modifier = Modifier.wrapContentSize()) {
        TextFieldWithHint(stringResource(string.username))
        TextFieldWithHint(stringResource(string.password))
    }
}

@Composable
fun loginButton(onClick:(Int) -> Unit) {
    ClickableText(
        text = AnnotatedString("Login"),
        onClick = onClick,
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
fun TextFieldWithHint(labelText: String) {
    TextField(
        value = "",
        onValueChange = {},
        label = { Text(labelText) },
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
