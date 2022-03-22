package com.squadsandshots_android.di

import com.squadsandshots_android.repositories.DataBaseRepoInterface
import com.squadsandshots_android.repositories.FirebaseDataRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideFirebaseDataRepo(
        firebaseDataRepo: FirebaseDataRepo
    ): DataBaseRepoInterface


}
