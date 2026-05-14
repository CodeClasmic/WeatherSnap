package com.project.weathersnap.ui.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.weathersnap.viewmodel.ReportSharedViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val DarkBg =
    Color(0xFF0B1207)

private val DarkCard =
    Color(0xFF242424)

private val Olive =
    Color(0xFFD7E88F)

private val HumidityColor =
    Color(0xFF5BC0A5)

private val WindColor =
    Color(0xFF6FA8FF)

private val PressureColor =
    Color(0xFFFFB870)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedReportsScreen(

    navController: NavController,

    sharedViewModel: ReportSharedViewModel
) {

    val reports by
    sharedViewModel.reports
        .collectAsState(
            initial = emptyList()
        )

    Scaffold(

        containerColor = DarkBg,

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "Saved Reports",

                        color = Olive,

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ArrowBack,

                            contentDescription =
                                "Back",

                            tint = Olive
                        )
                    }
                },

                colors =
                    TopAppBarDefaults
                        .topAppBarColors(
                            containerColor = DarkBg
                        )
            )
        }
    ) { paddingValues ->

        if (reports.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text =
                        "No saved reports yet.",

                    color = Color.White,

                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkBg)
                    .padding(paddingValues)
                    .padding(16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(16.dp)
            ) {

                items(reports) { report ->

                    Card(
                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(24.dp),

                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    DarkCard
                            )
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(16.dp),

                            verticalArrangement =
                                Arrangement.spacedBy(14.dp)
                        ) {

                            if (!report.imageUri.isNullOrEmpty()) {

                                AsyncImage(

                                    model =
                                        report.imageUri,

                                    contentDescription =
                                        null,

                                    modifier =
                                        Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1f),

                                    contentScale =
                                        ContentScale.Crop
                                )
                            }

                            Row(
                                modifier =
                                    Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.SpaceBetween,

                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Column(
                                    modifier =
                                        Modifier.weight(1f)
                                ) {

                                    Text(
                                        text =
                                            report.city,

                                        style =
                                            MaterialTheme
                                                .typography
                                                .titleMedium,

                                        color =
                                            Color.White
                                    )

                                    Text(
                                        text =
                                            report.condition,

                                        color =
                                            Color.LightGray,

                                        style =
                                            MaterialTheme
                                                .typography
                                                .bodySmall
                                    )
                                }

                                Card(
                                    shape =
                                        RoundedCornerShape(
                                            18.dp
                                        ),

                                    colors =
                                        CardDefaults
                                            .cardColors(
                                                containerColor =
                                                    Color(
                                                        0xFF556B12
                                                    )
                                            )
                                ) {

                                    Text(
                                        text =
                                            report.temperature,

                                        modifier =
                                            Modifier.padding(
                                                horizontal = 16.dp,
                                                vertical = 10.dp
                                            ),

                                        color =
                                            Olive,

                                        style =
                                            MaterialTheme
                                                .typography
                                                .bodyMedium
                                    )
                                }
                            }

                            Row(
                                modifier =
                                    Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                WeatherMetricItem(
                                    title = "Humidity",

                                    value =
                                        report.humidity,

                                    color =
                                        HumidityColor
                                )

                                WeatherMetricItem(
                                    title = "Wind",

                                    value =
                                        report.wind,

                                    color =
                                        WindColor
                                )

                                WeatherMetricItem(
                                    title = "Pressure",

                                    value =
                                        report.pressure,

                                    color =
                                        PressureColor
                                )
                            }

                            Text(
                                text =
                                    "Notes",

                                color =
                                    Olive,

                                style =
                                    MaterialTheme
                                        .typography
                                        .bodyMedium
                            )

                            Text(
                                text =
                                    report.notes,

                                color =
                                    Color.White.copy(
                                        alpha = 0.85f
                                    ),

                                style =
                                    MaterialTheme
                                        .typography
                                        .bodySmall
                            )

                            Text(
                                text =
                                    formatDate(
                                        report.timestamp
                                    ),

                                color =
                                    Color.Gray,

                                style =
                                    MaterialTheme
                                        .typography
                                        .bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherMetricItem(
    title: String,
    value: String,
    color: Color
) {

    Column {

        Text(
            text = title,

            color =
                Color.LightGray,

            style =
                MaterialTheme
                    .typography
                    .bodySmall
        )

        Spacer(
            modifier =
                Modifier.height(4.dp)
        )

        Text(
            text = value,

            color = color,

            style =
                MaterialTheme
                    .typography
                    .bodySmall
        )
    }
}

fun formatDate(
    timestamp: Long
): String {

    val formatter =
        SimpleDateFormat(
            "dd MMM yyyy, hh:mm a",
            Locale.getDefault()
        )

    return formatter.format(
        Date(timestamp)
    )
}