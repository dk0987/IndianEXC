package com.example.indianexc.data.remote

import com.example.indianexc.data.remote.dto.response.LatestExchangeRateResponse
import com.example.indianexc.util.Constants
import com.example.indianexc.util.Constants.API_KEY
import com.example.indianexc.util.Constants.currenciesNames
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateAPI {

//    https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_twHvc6H1Kcn8H0gX2EdZE2FCl9lq90sDU5XdR0zB&currencies=EUR%2CUSD%2CNZD%2CJPY%2CRUB&base_currency=INR
    @GET("/v1/latest?apikey=fca_live_twHvc6H1Kcn8H0gX2EdZE2FCl9lq90sDU5XdR0zB&currencies=EUR%2CUSD%2CNZD%2CJPY%2CRUB&base_currency=INR")
    suspend fun getLatestExchangeRate(): LatestExchangeRateResponse


}