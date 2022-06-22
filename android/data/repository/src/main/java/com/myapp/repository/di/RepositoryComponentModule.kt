package com.myapp.repository.di

import com.myapp.kmmsample.repository.LocalCoinRepository
import com.myapp.repository.LocalCoinRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryComponentModule {

    @Binds
    abstract fun bindLocalMissionStatementRepository(
        localCoinRepositoryImpl: LocalCoinRepositoryImpl
    ): LocalCoinRepository

}