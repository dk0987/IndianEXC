package com.example.indianexc.data.repository

import com.example.indianexc.data.local.db.ExchangeRateDB
import com.example.indianexc.data.mapper.toExchangeRate
import com.example.indianexc.data.mapper.toExchangeRateEntity
import com.example.indianexc.data.remote.ExchangeRateAPI
import com.example.indianexc.domain.modal.ExchangeRate
import com.example.indianexc.domain.repository.ExchangeRateRepository
import com.example.indianexc.util.Resource
import com.example.indianexc.util.isTimestampToday
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExchangeRateRepositoryImpl @Inject constructor(
    private val api : ExchangeRateAPI,
    db : ExchangeRateDB
)  : ExchangeRateRepository{

    private val exchangeRateDao = db.dao

    override suspend fun getExchangeRates(isOnline: Boolean): Flow<Resource<List<ExchangeRate>>> {
       return flow{
           emit(Resource.Loading(true))
           when(exchangeRateDao.getExchangeRates().isEmpty()){
                true -> {
                   if (isOnline){
                       try {
                           val fetchData = api.getLatestExchangeRate()
                           val exchangeRateEntity = fetchData.toExchangeRateEntity()
                           exchangeRateDao.insertExchangeRate(exchangeRateEntity)
                           val exchangeRates = exchangeRateDao.getExchangeRates().map { it.toExchangeRate() }
                           emit(Resource.Loading(false))
                           emit(Resource.Success(exchangeRates))
                       }
                       catch (_ : IOException){
                           emit(Resource.Loading(false))
                           emit(Resource.Error("Something Went Wrong"))
                       }
                       catch (_ : HttpException){
                           emit(Resource.Loading(false))
                           emit(Resource.Error("Connection Problem"))
                       }

                   } else {
                       emit(Resource.Loading(false))
                       emit(Resource.Success(emptyList()))
                   }
               }
               false -> {
                   val isTodayExchangeRate = exchangeRateDao.getTodayExchangeRate()?.timestamp?.isTimestampToday()
                   if (isTodayExchangeRate == true){
                       val exchangeRates = exchangeRateDao.getExchangeRates().map { it.toExchangeRate() }
                       emit(Resource.Loading(false))
                       emit(Resource.Success(exchangeRates))
                   }
                   else{
                       try {
                           val fetchData = api.getLatestExchangeRate()
                           val exchangeRateEntity = fetchData.toExchangeRateEntity()
                           exchangeRateDao.insertExchangeRate(exchangeRateEntity)
                           val exchangeRates = exchangeRateDao.getExchangeRates().map { it.toExchangeRate() }
                           emit(Resource.Loading(false))
                           emit(Resource.Success(exchangeRates))
                       }
                       catch (_ : IOException){
                           emit(Resource.Loading(false))
                           emit(Resource.Error("Something Went Wrong"))
                       }
                       catch (_ : HttpException){
                           emit(Resource.Loading(false))
                           emit(Resource.Error("Connection Problem"))
                       }
                   }
               }
           }
       }
    }

    override suspend fun getTodayExchangeRate(): Flow<Resource<ExchangeRate?>> = flow {
        emit(Resource.Loading(true))
        val todayExchangeRate = exchangeRateDao.getTodayExchangeRate()
        emit(Resource.Success(todayExchangeRate?.toExchangeRate()))
        emit(Resource.Loading(false))
    }
}