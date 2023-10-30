package com.example.indianexc.data.mapper

import com.example.indianexc.data.local.entity.ExchangeRateEntity
import com.example.indianexc.data.remote.dto.response.LatestExchangeRateResponse
import com.example.indianexc.domain.modal.Currency
import com.example.indianexc.domain.modal.ExchangeRate
import com.example.indianexc.util.Countries.Europe
import com.example.indianexc.util.Countries.India
import com.example.indianexc.util.Countries.Japan
import com.example.indianexc.util.Countries.NewZealand
import com.example.indianexc.util.Countries.Russia
import com.example.indianexc.util.Countries.UnitedStates

fun ExchangeRateEntity.toExchangeRate() : ExchangeRate {
    return ExchangeRate(
        timestamp = timestamp,
        baseCountry = baseCountry,
        latestCurrencies = latestCurrencies
    )
}

fun LatestExchangeRateResponse.toExchangeRateEntity() : ExchangeRateEntity{
    return ExchangeRateEntity(
        timestamp = System.currentTimeMillis(),
        baseCountry = India,
        latestCurrencies = listOf(
            Currency(
                country = Europe,
                rate = data.eur ?: 0.0
            ),
            Currency(
                country = UnitedStates,
                rate = data.usd ?: 0.0
            ),
            Currency(
                country = Russia,
                rate = data.rub ?: 0.0
            ),
            Currency(
                country = NewZealand,
                rate = data.nzd ?: 0.0
            ),
            Currency(
                country = Japan,
                rate = data.jpy ?: 0.0
            )
        )
    )
}