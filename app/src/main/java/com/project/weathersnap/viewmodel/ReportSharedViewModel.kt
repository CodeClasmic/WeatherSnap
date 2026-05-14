package com.project.weathersnap.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.project.weathersnap.database.DatabaseProvider
import com.project.weathersnap.database.WeatherReportEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReportSharedViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var hasSavedReport = false

    private val dao =
        DatabaseProvider
            .getDatabase(application)
            .weatherReportDao()

    private val _imageUri =
        MutableStateFlow<Uri?>(null)

    val imageUri =
        _imageUri.asStateFlow()

    val reports =
        dao.getAllReports()

    fun setImageUri(
        uri: Uri?
    ) {

        _imageUri.value = uri
    }

    fun saveReport(
        city: String,
        temperature: String,
        condition: String,
        notes: String,
        humidity: String,
        wind: String,
        pressure: String,
        onSaved: () -> Unit
    ) {

        if (hasSavedReport) return

        hasSavedReport = true

        val currentImageUri =
            _imageUri.value?.toString()

        viewModelScope.launch {

            dao.insertReport(

                WeatherReportEntity(

                    city = city,

                    temperature = temperature,

                    condition = condition,

                    notes = notes,

                    imageUri = currentImageUri,

                    humidity = humidity,
                    wind = wind,
                    pressure = pressure,

                    timestamp =
                        System.currentTimeMillis()
                )
            )

            onSaved()
        }
    }

    fun clearReportState() {

        _imageUri.value = null

        hasSavedReport = false
    }
}