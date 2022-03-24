package com.squadsandshots_android.useCases

import com.google.android.gms.tasks.Task
import com.squadsandshots_android.core.utils.BaseUseCase
import com.squadsandshots_android.repositories.DataBaseRepoInterface

class RequestResetPasswordUseCase(val dataBaseRepoInterface: DataBaseRepoInterface)
    : BaseUseCase<Task<Void>, String>() {
    override suspend fun run(params: String): Result<Task<Void>, Exception> {
        return try {
            Result.Success(dataBaseRepoInterface.sendPasswordResetRequest(params))
        } catch (ex: Exception) {
            Result.Failure(ex)
        }
    }
}