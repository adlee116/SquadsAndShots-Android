package com.squadsandshots_android.presentation.nominationPage

import com.squadsandshots_android.firebaseModels.Player
import com.squadsandshots_android.firebaseModels.Rule

data class NominationDetails(
    val availablePlayers: List<Player>,
    val rule: Rule,
    val roomCode: String
)
