package com.squadsandshots_android.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squadsandshots_android.requestModels.LoginRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseDataRepo @Inject constructor(): DataBaseRepoInterface {

    private val databaseReference = Firebase.database
    private val firebaseAuth = Firebase.auth

    override fun createUser(createUserRequest: LoginRequest): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(createUserRequest.username, createUserRequest.password)
    }

    override fun login(loginRequest: LoginRequest): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(loginRequest.username, loginRequest.password)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

}