package com.example.indianexc.presentation.home

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.indianexc.domain.repository.ExchangeRateRepository
import com.example.indianexc.domain.util.ConnectivityObserver
import com.example.indianexc.domain.util.NetworkConnectivityObserver
import com.example.indianexc.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository : ExchangeRateRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeStates())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                error = null,
            )
            getExchangeRates(state.value.online)
            getTodayExchangeRate()
        }
    }

    fun onEvent(event : HomeEvent) {
        when(event){
            is HomeEvent.SelectedIndex -> {
                _state.value = state.value.copy(
                    selectedIndex = event.data
                )
                getExchangeRates(state.value.online)
            }
        }
    }

    private fun getTodayExchangeRate(){
        viewModelScope.launch {
            val result = repository.getTodayExchangeRate()
            result.collectLatest {data ->
                when(data){
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                           loadingExchangeRate = data.isLoading
                        )
                    }
                    is Resource.Success -> {
                        data.data?.let {
                            _state.value = state.value.copy(
                                latestExchangeRate = it,
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            error = data.message
                        )
                    }
                }
            }
        }
    }

    private fun getExchangeRates(isOnline : Boolean){
        viewModelScope.launch {
            val result = repository.getExchangeRates(isOnline)
            result.collectLatest { data ->
                when(data){
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            loadingExchangeRateList = data.isLoading
                        )
                    }
                    is Resource.Success -> {
                        data.data?.let {
                            _state.value = state.value.copy(
                                exchangeRates = it.subList(0 , 8),
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            error = data.message
                        )
                    }
                }
            }
        }
    }

    suspend fun observeInternet(context : Context) {
        viewModelScope.launch {
            val connectivityObserver = NetworkConnectivityObserver(context)
            connectivityObserver.observer().collectLatest {
                if (it == ConnectivityObserver.Status.Available) {
                    _state.value = state.value.copy(
                        online = true
                    )
                } else {
                    _state.value = state.value.copy(
                        online = false
                    )
                }
            }

        }
    }
}