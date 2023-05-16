package com.squadsandshots_android.firebaseModels

import com.google.firebase.database.ValueEventListener

class ListenerRequest(
    val listener: ValueEventListener,
    val roomCode: String
)