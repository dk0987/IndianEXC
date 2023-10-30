package com.example.indianexc.di

import android.app.Application
import androidx.room.Room
import com.example.indianexc.data.local.db.ExchangeRateDB
import com.example.indianexc.data.remote.ExchangeRateAPI
import com.example.indianexc.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : ExchangeRateAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ExchangeRateAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRateDB(context : Application) : ExchangeRateDB {
        return Room.databaseBuilder(
            context,
            ExchangeRateDB::class.java,
            "Exchange Rate"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}