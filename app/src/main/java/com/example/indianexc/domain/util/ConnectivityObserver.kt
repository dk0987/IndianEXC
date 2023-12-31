package com.example.indianexc.domain.util

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {

    fun observer() : Flow<Status>

    enum class Status{
        Available , Unavailable , Losing , Lost
    }
}