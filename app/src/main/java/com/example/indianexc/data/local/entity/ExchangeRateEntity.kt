package com.example.indianexc.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.indianexc.domain.modal.Country
import com.example.indianexc.domain.modal.Currency

@Entity
data class ExchangeRateEntity(
    val timestamp : Long,
    val baseCountry: Country,
    val latestCurrencies: List<Currency>,
    @PrimaryKey val id : Int? = null
)