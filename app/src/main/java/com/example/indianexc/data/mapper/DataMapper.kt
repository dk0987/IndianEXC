package com.example.indianexc.data.mapper

import com.example.indianexc.data.remote.dto.response.Data
import com.example.indianexc.domain.modal.Currency
import com.example.indianexc.util.Countries.Europe
import com.example.indianexc.util.Countries.Japan
import com.example.indianexc.util.Countries.NewZealand
import com.example.indianexc.util.Countries.Russia
import com.example.indianexc.util.Countries.UnitedStates

fun Data.toCurrencies() : List<Currency>{
    return listOf(
        Currency(
            country = Europe,
            rate = eur ?: 0.0
        ),
        Currency(
            country = Russia,
            rate = rub ?: 0.0
        ),
        Currency(
            country = UnitedStates,
            rate = usd ?: 0.0
        ),
        Currency(
            country = Japan,
            rate = jpy ?: 0.0
        ),
        Currency(
            country = NewZealand,
            rate = nzd ?: 0.0
        ),
    )
}