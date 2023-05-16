package com.squadsandshots_android.firebaseModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Rule: Parcelable {
    var id: Int = -1
    var title: String = ""
    var description: String = ""
    var image: String = ""
    var players: MutableList<Player> = mutableListOf()
    var ruleFunnyString = ""
}