package com.example.indianexc.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.indianexc.data.local.entity.ExchangeRateEntity

@Dao
interface ExchangeRateDAO {

    @Query("SELECT * FROM ExchangeRateEntity ORDER BY timestamp DESC ")
    suspend fun getExchangeRates() : List<ExchangeRateEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRate(exchangeRateEntity: ExchangeRateEntity)

    @Query("SELECT * FROM ExchangeRateEntity ORDER BY timestamp DESC LIMIT 1")
    suspend fun getTodayExchangeRate() : ExchangeRateEntity?
}