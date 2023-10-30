package com.example.indianexc.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.indianexc.data.local.converters.Converter
import com.example.indianexc.data.local.dao.ExchangeRateDAO
import com.example.indianexc.data.local.entity.ExchangeRateEntity

@Database(
    entities = [ExchangeRateEntity :: class],
    version = 4
)
@TypeConverters(Converter::class)
abstract class ExchangeRateDB : RoomDatabase(){
    abstract val dao : ExchangeRateDAO
}