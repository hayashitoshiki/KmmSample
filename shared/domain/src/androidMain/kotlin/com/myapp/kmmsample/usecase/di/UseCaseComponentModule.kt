package com.myapp.kmmsample.usecase.di

import com.myapp.kmmsample.usecase.CoinUseCase
import com.myapp.kmmsample.usecase.CoinUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseComponentModule {

    @Binds
    abstract fun bindCoinUseCase(coinUseCaseImpl: CoinUseCaseImpl): CoinUseCase

}