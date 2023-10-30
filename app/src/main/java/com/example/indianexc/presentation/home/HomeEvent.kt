package com.example.indianexc.presentation.home

sealed class HomeEvent{
    data class SelectedIndex(val data : Int) : HomeEvent()
}
