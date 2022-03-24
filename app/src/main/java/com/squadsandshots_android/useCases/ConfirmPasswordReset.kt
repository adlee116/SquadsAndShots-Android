package com.squadsandshots_android.useCases

import com.google.android.gms.tasks.Task
import com.squadsandshots_android.core.utils.BaseUseCase
import com.squadsandshots_android.repositories.DataBaseRepoInterface

class ConfirmPasswordReset(val dataBaseRepoInterface: DataBaseRepoInterface):
    BaseUseCase<Task<String>, String>() {
    override suspend fun run(params: String): Result<Task<String>, Exception> {
        return try {
            Result.Success(dataBaseRepoInterface.verifyPasswordResetCode(params))
        } catch (ex: Exception) {
            Result.Failure(ex)
        }
    }
}