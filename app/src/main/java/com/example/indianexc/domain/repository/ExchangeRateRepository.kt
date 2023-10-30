package com.example.indianexc.domain.repository

import com.example.indianexc.domain.modal.ExchangeRate
import com.example.indianexc.util.Resource
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {

    suspend fun getExchangeRates(isOnline : Boolean) : Flow<Resource<List<ExchangeRate>>>

    suspend fun getTodayExchangeRate() : Flow<Resource<ExchangeRate?>>
}