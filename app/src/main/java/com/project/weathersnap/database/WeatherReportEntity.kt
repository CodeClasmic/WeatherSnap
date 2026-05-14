package com.project.weathersnap.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "weather_reports"
)
data class WeatherReportEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val city: String,

    val temperature: String,

    val condition: String,

    val notes: String,

    val imageUri: String?,

    val timestamp: Long,

    val humidity: String,

    val wind: String,

    val pressure: String,
)