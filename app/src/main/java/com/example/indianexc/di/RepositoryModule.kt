package com.example.indianexc.di

import com.example.indianexc.data.repository.ExchangeRateRepositoryImpl
import com.example.indianexc.domain.repository.ExchangeRateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExchangeRateRepository(
        exchangeRateRepository: ExchangeRateRepositoryImpl
    ) : ExchangeRateRepository

}