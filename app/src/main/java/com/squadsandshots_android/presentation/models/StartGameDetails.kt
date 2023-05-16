package com.squadsandshots_android.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StartGameDetails(
    val createGameStart: CreateGameStart?,
    val joinGameStart: JoinGameStart?
): Parcelable

@Parcelize
data class CreateGameStart(
    val name: String,
    val gameType: GameType
): Parcelable

@Parcelize
data class JoinGameStart(
    val name: String,
    val gameCode: String,
): Parcelable