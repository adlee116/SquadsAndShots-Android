package com.squadsandshots_android.useCases

import com.google.firebase.auth.FirebaseUser
import com.squadsandshots_android.core.utils.BaseUseCase
import com.squadsandshots_android.repositories.DataBaseRepoInterface
import com.squadsandshots_android.requestModels.LoginRequest
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val dataBaseRepoInterface: DataBaseRepoInterface
) {
//    override suspend fun run(params: LoginRequest): Result<FirebaseUser, Exception> {
//        return try {
//            val user = dataBaseRepoInterface.login(params).result.user
//            user?.let { Result.Success(it) } ?: run { Result.Failure(Exception()) }
//        } catch (ex: Exception) {
//            ZResult.Failure(ex)
//        }
//    }

}