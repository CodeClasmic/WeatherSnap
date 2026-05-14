package com.project.weathersnap.ui.weather

import android.service.notification.Condition

sealed interface WeatherUiState{
    data object Empty : WeatherUiState
    data object Loading : WeatherUiState

    data class Success(
        val city : String,
        val temperature : String,
        val humidity: String,
        val condition: String,
        val windSpeed: String,
        val pressure: String
    ) : WeatherUiState

    data class Error(
        val message: String
    ) : WeatherUiState
}