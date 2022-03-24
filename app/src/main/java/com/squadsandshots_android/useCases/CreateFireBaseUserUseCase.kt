package com.squadsandshots_android.useCases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.squadsandshots_android.core.utils.BaseUseCase
import com.squadsandshots_android.repositories.DataBaseRepoInterface
import com.squadsandshots_android.requestModels.LoginRequest
import javax.inject.Inject

class CreateFireBaseUserUseCase @Inject constructor(
    private val dataBaseRepoInterface: DataBaseRepoInterface
): BaseUseCase<Task<AuthResult>, LoginRequest>() {
    override suspend fun run(params: LoginRequest): Result<Task<AuthResult>, Exception> {
        return try {
            Result.Success(dataBaseRepoInterface.createUser(params))
        } catch (ex: Exception) {
            Result.Failure(ex)
        }
    }
}