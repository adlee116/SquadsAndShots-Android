package com.squadsandshots_android.firebaseModels

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squadsandshots_android.presentation.models.GameType
import kotlinx.parcelize.Parcelize

data class RoomModel (
    var players: HashMap<String, Player> = HashMap(),
    var generalRules: List<GeneralRule>? = null,
    var nominatedRules: List<NominatedRule>? = null,
    var secretTasks: List<SecretTasks>? = null,
    var roomCode: String = "",
    var gameType: GameType = GameType.WARZONE
)

@Parcelize
@Entity
data class Player(
    @PrimaryKey
    var id: String = "",
    var name: String = ""
) : Parcelable
