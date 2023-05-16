package com.squadsandshots_android.presentation.mainGameRoom

import android.widget.Button
import android.widget.EditText
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.squadsandshots_android.presentation.nominationPage.BasicLobbyCode
import com.squadsandshots_android.presentation.nominationPage.PlayerList

@Destination
@Composable
fun MainGameRoom(lobbyCode: String, viewModel: MainGameRoomViewModel = hiltViewModel()) {
    Column {
        BasicLobbyCode(code = lobbyCode)
        PlayerList(playerList = viewModel.players.value) {

        }
        //TODO secret task list
        //TODO nominated task list
        //TODO general task list

    }
}

fun CreateList(): List<Any> {
    val mutableList: List<Any> = mutableListOf()
    ButtonType(text = "Button 1")
    StringType(text = "String 1")
    ButtonType(text = "Button 2")
    StringType(text = "String 2")
    return mutableList
}

@Preview
@Composable
fun ListTest() {
    val items = CreateList()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) {
            when(it) {
                is ButtonType -> { ItemTwo(text = it.text) }
                is StringType -> { ItemOne(text = it.text) }
            }

        }

    }
}

@Composable
fun ItemOne(text: String) {
    Text(text, modifier = Modifier.fillMaxWidth().height(22.dp))
}

@Composable
fun ItemTwo(text: String) {
    Button(onClick = {}) {
        Text(text = text)
    }
}

data class ButtonType(
    val text: String = "Button"
)

data class StringType(
    val text: String = "Text"
)