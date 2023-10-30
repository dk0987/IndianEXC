package com.example.indianexc.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.isTimestampToday(): Boolean {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = dateFormat.format(Date())

    val timestampDate = dateFormat.format(Date(this))
    return currentDate == timestampDate

}

fun Long.toDay() : String{
    val date = Date(this)
    val simpleDateFormat =  SimpleDateFormat("EEEE" , Locale.getDefault())
    return simpleDateFormat.format(date).substring(0 , 3)
}