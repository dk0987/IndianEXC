package com.example.indianexc.util

sealed class Resource<T>(data : T? = null , error : String? = null , isLoading: Boolean = false){
    data class Success<T>(val data: T?) : Resource<T>(data)
    data class Error<T>(val message : String?) : Resource<T>(error = message)
    data class Loading<T>(val isLoading : Boolean ) : Resource<T>(isLoading = isLoading)
}
