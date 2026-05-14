package com.project.weathersnap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.weathersnap.data.remote.api.RetrofitInstance
import com.project.weathersnap.ui.weather.CitySuggestion
import com.project.weathersnap.ui.weather.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow<WeatherUiState>(
            WeatherUiState.Empty
        )

    val uiState = _uiState.asStateFlow()

    private val _suggestions =
        MutableStateFlow<List<CitySuggestion>>(
            emptyList()
        )

    val suggestions =
        _suggestions.asStateFlow()

    fun searchCities(
        query: String
    ) {

        if (query.length <= 2) {

            _suggestions.value =
                emptyList()

            return
        }

        viewModelScope.launch {

            try {

                val response =
                    RetrofitInstance
                        .geocodingApi
                        .searchCities(query)

                _suggestions.value =
                    response.results
                        ?.distinctBy {

                            "${it.name}-${it.country}"
                        }

                        ?.map {

                            CitySuggestion(
                                name = it.name,
                                country = it.country,
                                latitude = it.latitude,
                                longitude = it.longitude
                            )
                        }

                        ?: emptyList()

            } catch (e: Exception) {

                e.printStackTrace()

                _suggestions.value =
                    emptyList()
            }
        }
    }

    fun loadWeather(
        city: CitySuggestion
    ) {

        viewModelScope.launch {

            try {

                _uiState.value =
                    WeatherUiState.Loading

                clearSuggestions()

                val response =
                    RetrofitInstance
                        .weatherApi
                        .getWeather(
                            latitude = city.latitude,
                            longitude = city.longitude
                        )

                val current =
                    response.current

                _uiState.value =
                    WeatherUiState.Success(

                        city =
                            "${city.name}, ${city.country}",

                        temperature =
                            "${current.temperature}°C",

                        condition =
                            getWeatherCondition(
                                current.weatherCode
                            ),

                        humidity =
                            "${current.humidity}%",

                        windSpeed =
                            "${current.windSpeed} km/h",

                        pressure =
                            "${current.pressure} hPa"
                    )

            } catch (e: Exception) {

                e.printStackTrace()

                _uiState.value =
                    WeatherUiState.Error(
                        e.message
                            ?: "Something went wrong"
                    )
            }
        }
    }

    private fun getWeatherCondition(
        code: Int
    ): String {

        return when (code) {

            0 -> "Clear Sky"

            1, 2, 3 -> "Partly Cloudy"

            45, 48 -> "Fog"

            51, 53, 55 -> "Drizzle"

            61, 63, 65 -> "Rain"

            71, 73, 75 -> "Snow"

            95 -> "Thunderstorm"

            else -> "Unknown"
        }
    }

    fun clearSuggestions() {

        _suggestions.value =
            emptyList()
    }
}