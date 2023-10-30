package com.example.indianexc.presentation.home

import com.example.indianexc.domain.modal.ExchangeRate

data class HomeStates(
    val loadingExchangeRateList : Boolean = false,
    val loadingExchangeRate : Boolean = false,
    val error : String? = null,
    val exchangeRates : List<ExchangeRate> = emptyList(),
    val latestExchangeRate : ExchangeRate? = null,
    val selectedIndex : Int = 4,
    val online : Boolean = false
)
