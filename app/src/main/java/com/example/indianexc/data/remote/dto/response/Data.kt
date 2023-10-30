package com.example.indianexc.data.remote.dto.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Data(
    @field:Json(name = "EUR")val eur: Double?,
    @field:Json(name = "JPY")val jpy: Double?,
    @field:Json(name = "NZD")val nzd: Double?,
    @field:Json(name = "RUB")val rub: Double?,
    @field:Json(name = "USD")val usd: Double?
)