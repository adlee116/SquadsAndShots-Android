package com.squadsandshots_android.useCases

import android.app.Service
import com.google.firebase.auth.FirebaseUser
import com.squadsandshots_android.core.utils.BaseUseCase
import com.squadsandshots_android.repositories.DataBaseRepoInterface
import com.squadsandshots_android.requestModels.LoginRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor() {
    @Inject
    lateinit var dataBaseRepoInterface: DataBaseRepoInterface
    operator fun invoke(
        scope: CoroutineScope,
        params: LoginRequest,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        scope.launch { withContext(Dispatchers.IO) {
            run(params, onSuccess, onFailure) }
        }
    }

    private fun run(
        params: LoginRequest,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Exception) -> Unit
    ){
        return try {
            val userResponse = dataBaseRepoInterface.login(params)
            if (userResponse.isSuccessful) {
                userResponse.result.user?.let { onSuccess(it)
                } ?: run {
                    onFailure(Exception(""))
                }
            } else {
                onFailure(Exception(""))
            }
        } catch (ex: Exception) {
            onFailure(Exception(""))
        }
    }
}