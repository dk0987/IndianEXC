package com.example.indianexc.util

fun Double.round() : Double{
    return String.format("%.6f",this).toDouble()
}