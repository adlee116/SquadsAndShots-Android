package com.squadsandshots_android.di

import com.squadsandshots_android.repositories.DataBaseRepoInterface
import com.squadsandshots_android.repositories.FirebaseDataRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFirebaseDataRepo(): DataBaseRepoInterface {
        return FirebaseDataRepo()
    }


}
