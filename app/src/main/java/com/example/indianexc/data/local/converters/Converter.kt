package com.example.indianexc.data.local.converters

import androidx.room.TypeConverter
import com.example.indianexc.domain.modal.Country
import com.example.indianexc.domain.modal.Currency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class Converter {

    private val gson = Gson()

    @TypeConverter
    fun fromCountry(country: Country) : String{
        return gson.toJson(country)
    }

    @TypeConverter
    fun toCountry(countryString : String) : Country{
        return gson.fromJson(countryString , Country::class.java)
    }

    @TypeConverter
    fun fromListOfCurrency(currencies : List<Currency>) : String{
        return gson.toJson(currencies)
    }

    @TypeConverter
    fun toListOfCurrency(currenciesString : String) : List<Currency>{
        val type = object : TypeToken<List<Currency>>() {}.type
        return gson.fromJson(currenciesString , type)
    }

}