package com.squadsandshots_android.presentation.nominationPage

import androidx.lifecycle.ViewModel
import com.squadsandshots_android.firebaseModels.Player
import com.squadsandshots_android.firebaseModels.Rule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NominationViewModel @Inject constructor(): ViewModel() {

    fun nominatePlayer(rule: Rule, player: Player) {
        rule.players.add(player)
    }

}