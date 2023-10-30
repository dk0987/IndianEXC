package com.example.indianexc.domain.modal

data class ExchangeRate(
    val timestamp : Long,
    val baseCountry: Country,
    val latestCurrencies: List<Currency>,
)
