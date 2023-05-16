package com.squadsandshots_android.firebaseModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class NominatedRule: Rule(), Parcelable {
    val nominatedPlayer: Player = Player()
}