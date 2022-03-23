package com.squadsandshots_android.useCases

import com.google.firebase.auth.FirebaseUser
import com.squadsandshots_android.repositories.DataBaseRepoInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentFireBaseUserUseCase @Inject constructor(
    private val dataBaseRepoInterface: DataBaseRepoInterface
) {

    operator fun invoke(
        scope: CoroutineScope,
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        scope.launch {
            withContext(Dispatchers.IO) {
                run(onSuccess, onFailure)
            }
        }
    }

    private fun run(
        onSuccess: (FirebaseUser?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
//        return try {
//            onSuccess(dataBaseRepoInterface.getCurrentUser())
//        } catch (ex: Exception) {
//            onFailure(Exception(""))
//        }
    }
}