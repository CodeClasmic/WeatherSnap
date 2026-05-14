package com.project.weathersnap.data.remote.api

import com.project.weathersnap.data.remote.dto.GeoCodingResponseDto
import com.project.weathersnap.data.remote.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/search")
    suspend fun searchCities(

        @Query("name")
        city: String

    ): GeoCodingResponseDto


    @GET("v1/forecast")
    suspend fun getWeather(

        @Query("latitude")
        latitude: Double,

        @Query("longitude")
        longitude: Double,

        @Query("current")
        current: String =
            "temperature_2m,relative_humidity_2m,pressure_msl,wind_speed_10m,weather_code"
    ): WeatherResponseDto
}