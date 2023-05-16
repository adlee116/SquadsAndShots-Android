package com.squadsandshots_android.presentation.nominationPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.squadsandshots_android.R
import com.squadsandshots_android.firebaseModels.Player
import com.squadsandshots_android.presentation.preGameLobby.brush

@Composable
fun NominationPage(
    nominationDetails: NominationDetails,
    nominationViewModel: NominationViewModel = hiltViewModel()
) {
    Column {
        BasicLobbyCode(code = nominationDetails.roomCode)
        Text(
            text = "Nomination required for the following rules",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        )
        RuleDetails(nominationDetails.rule.description)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp))
        Text(
            text = "Which player would you like to nominate?",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        )
        PlayerList(playerList = nominationDetails.availablePlayers
        ) { nominationViewModel.nominatePlayer(rule = nominationDetails.rule, player = it) }
    }
}

@Composable
fun BasicLobbyCode(code: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Text(text = stringResource(R.string.lobby_code), modifier = Modifier.size(16.dp))
            Text(text = code)
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
fun RuleDetails(rule: String) {
    Text(
        text =  rule,
        fontSize = 24.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 22.dp)
            .background(brush = brush(), shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 30.dp, vertical = 50.dp)
            .fillMaxWidth()
    )
}

@Composable
fun PlayerList(playerList: List<Player>, onClick: (Player) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 22.dp)
    ) {
        items(items = playerList) { player ->
            PlayerItem(player = player, onClick)
        }
    }
}

@Composable
fun PlayerItem(player: Player, onClick: (Player) -> Unit) {
    ClickableText(
        text = AnnotatedString(player.name),
        onClick = { onClick(player) },
        style = TextStyle(
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(brush = brush(), shape = RoundedCornerShape(5.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
