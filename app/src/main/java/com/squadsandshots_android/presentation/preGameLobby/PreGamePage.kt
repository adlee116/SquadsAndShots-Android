package com.squadsandshots_android.presentation.preGameLobby

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.squadsandshots_android.R
import com.squadsandshots_android.presentation.models.StartGameDetails

@Destination
@Composable
fun PreGamePage(
    startGameDetails: StartGameDetails,
    viewModel: PreGameViewModel = hiltViewModel()
) {
    viewModel.init(startGameDetails)
    Column {
        LobbyCode(viewModel)
        Text("Players", modifier = Modifier.padding(16.dp))
        PlayersList(viewModel)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp))
        startGameDetails.createGameStart?.let {
            GradientButton("Start game") { viewModel.startGame() }
        } ?: run {
            WaitingForLeaderToStartGame()
        }
    }

}

@Composable
fun LobbyCode(viewModel: PreGameViewModel) {
    val roomCode = viewModel.roomCode.value
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Text(text = stringResource(R.string.lobby_code), modifier = Modifier.size(16.dp))
            Text(text = roomCode)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .height(1.dp)
        )
    }
}

@Composable
fun PlayersList(viewModel: PreGameViewModel) {
    val players = viewModel.players.value
    LazyColumn() {
        items(items = players) { player ->
            PlayerItem(player = player)
        }
    }
}

@Composable
fun PlayerItem(player: String) {
    Text(
        text = player,
        fontSize = 18.sp,
        color = Color.White,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(brush = brush(), shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit = { },
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(brush())
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun WaitingForLeaderToStartGame() {
    Text(
        text = "Waiting for leader to start game....",
        fontSize = 18.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}


//Previews

@Preview
@Composable
fun PlayersList() {
    val players = listOf("Aidan", "Nick", "Your Moma")
    LazyColumn() {
        items(items = players) { player ->
            PlayerItem(player = player)
        }
    }
}

fun brush() = Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
