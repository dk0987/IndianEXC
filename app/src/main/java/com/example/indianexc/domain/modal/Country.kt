package com.example.indianexc.domain.modal

import androidx.annotation.DrawableRes

data class Country(
    @DrawableRes val flag : Int ,
    val name : String,
    val currencyName : String
)
