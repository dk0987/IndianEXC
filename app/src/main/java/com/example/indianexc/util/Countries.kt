package com.example.indianexc.util

import com.example.indianexc.R
import com.example.indianexc.domain.modal.Country
import com.example.indianexc.util.Constants.currenciesNames

object Countries {

    val India = Country(
        flag = R.drawable.india,
        name = "IND",
        currencyName = currenciesNames[0]
    )

    val Russia = Country(
        flag = R.drawable.russia,
        name = "RUS",
        currencyName = currenciesNames[2]
    )

    val UnitedStates = Country(
        flag = R.drawable.unitedstates,
        name = "US",
        currencyName = currenciesNames[3]
    )

    val Europe = Country(
        flag = R.drawable.european_union,
        name = "EUR",
        currencyName = currenciesNames[5]
    )
    val Japan = Country(
        flag = R.drawable.japan,
        name = "JP",
        currencyName = currenciesNames[1]
    )
    val NewZealand = Country(
        flag = R.drawable.newzealand,
        name = "NZ",
        currencyName = currenciesNames[4]
    )



}