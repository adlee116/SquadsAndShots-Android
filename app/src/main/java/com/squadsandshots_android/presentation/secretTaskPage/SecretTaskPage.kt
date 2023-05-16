package com.squadsandshots_android.presentation.secretTaskPage

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.squadsandshots_android.presentation.nominationPage.NominationDetails
import com.squadsandshots_android.presentation.nominationPage.BasicLobbyCode
import com.squadsandshots_android.presentation.nominationPage.RuleDetails

@Destination
@Composable
fun SecretTaskPage(nominationDetails: NominationDetails, viewModel: SecretTaskViewModel = hiltViewModel()) {
    Column {
        BasicLobbyCode(code = nominationDetails.roomCode)
        Text(
            text = "Your secret task, if you are willing...",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
        )
        RuleDetails(nominationDetails.rule.description)
        Button(onClick = { viewModel.secretAccepted() }, modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            Text("I accept", textAlign = TextAlign.Center)
        }

    }
}





