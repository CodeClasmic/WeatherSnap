package com.project.weathersnap.ui.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.project.weathersnap.navigation.Screen
import com.project.weathersnap.viewmodel.WeatherViewModel

private val BackgroundColor = Color(0xFF0B1208)
private val HeaderColor = Color(0xFFDDEDBA)
private val CardColor = Color(0xFF1B1B1B)

private val Olive = Color(0xFFD7E887)

private val HumidityColor =
    Color(0xFF5BC0A5)

private val WindColor =
    Color(0xFF6FA8FF)

private val PressureColor =
    Color(0xFFFFB870)

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = viewModel()
) {

    val city = remember {
        mutableStateOf("")
    }

    val uiState by
    viewModel.uiState.collectAsState()

    val suggestions by
    viewModel.suggestions.collectAsState()

    Scaffold(
        containerColor = BackgroundColor
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            // HEADER

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(24.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            HeaderColor
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "WeatherSnap",

                        style =
                            MaterialTheme
                                .typography
                                .titleLarge,

                        fontWeight =
                            FontWeight.Bold,

                        color = Color.Black
                    )

                    Button(
                        onClick = {

                            navController.navigate(
                                Screen.SavedReports.route
                            ) {

                                launchSingleTop = true
                            }
                        },

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF5D6500)
                            ),

                        shape =
                            RoundedCornerShape(16.dp)
                    ) {

                        Text(
                            text = "Reports"
                        )
                    }
                }
            }

            // SEARCH CARD

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(24.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            CardColor
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(10.dp)
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically,

                        horizontalArrangement =
                            Arrangement.spacedBy(10.dp)
                    ) {

                        OutlinedTextField(
                            value = city.value,

                            onValueChange = {

                                city.value = it

                                viewModel.searchCities(it)
                            },

                            modifier =
                                Modifier.weight(1f),

                            label = {
                                Text("City")
                            },

                            singleLine = true,

                            shape =
                                RoundedCornerShape(16.dp)
                        )

                        Button(
                            onClick = { },

                            modifier =
                                Modifier.height(48.dp),

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        Olive,

                                    contentColor =
                                        Color.Black
                                ),

                            shape =
                                RoundedCornerShape(18.dp)
                        ) {

                            Text(
                                text = "Search"
                            )
                        }
                    }

                    Text(
                        text =
                            "Enter more than 2 letters to search.",

                        color =
                            Color.LightGray,

                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )

                    AnimatedVisibility(
                        visible =
                            suggestions.isNotEmpty(),

                        enter =
                            fadeIn() +
                                    expandVertically(),

                        exit =
                            fadeOut() +
                                    shrinkVertically()
                    ) {

                        Card(
                            modifier =
                                Modifier.fillMaxWidth(),

                            shape =
                                RoundedCornerShape(18.dp),

                            colors =
                                CardDefaults.cardColors(
                                    containerColor =
                                        Color(0xFF252525)
                                )
                        ) {

                            LazyColumn(
                                modifier =
                                    Modifier.height(
                                        220.dp
                                    )
                            ) {

                                items(suggestions) { suggestion ->

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {

                                                city.value =
                                                    suggestion.name

                                                viewModel.clearSuggestions()

                                                viewModel.loadWeather(
                                                    suggestion
                                                )
                                            }
                                            .padding(14.dp),

                                        horizontalArrangement =
                                            Arrangement.spacedBy(10.dp)
                                    ) {

                                        androidx.compose.material3.Icon(
                                            imageVector =
                                                Icons.Default.LocationOn,

                                            contentDescription = null,

                                            tint = Olive
                                        )

                                        Column {

                                            Text(
                                                text =
                                                    suggestion.name,

                                                color =
                                                    Color.White,

                                                style =
                                                    MaterialTheme
                                                        .typography
                                                        .bodyMedium
                                            )

                                            Text(
                                                text =
                                                    suggestion.country,

                                                color =
                                                    Color.LightGray,

                                                style =
                                                    MaterialTheme
                                                        .typography
                                                        .bodySmall
                                            )
                                        }
                                    }

                                    HorizontalDivider(
                                        color =
                                            Color.DarkGray
                                    )
                                }
                            }
                        }
                    }
                }
            }

            when (val state = uiState) {

                is WeatherUiState.Empty -> {

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )
                }

                is WeatherUiState.Loading -> {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        CircularProgressIndicator(
                            color = Olive
                        )
                    }
                }

                is WeatherUiState.Success -> {

                    WeatherCardModern(
                        state = state,

                        onCreateReport = {

                            navController.navigate(

                                Screen.CreateReport
                                    .createRoute(

                                        city = state.city,

                                        temperature =
                                            state.temperature,

                                        condition =
                                            state.condition,

                                        humidity =
                                            state.humidity,

                                        wind =
                                            state.windSpeed,

                                        pressure =
                                            state.pressure
                                    )
                            )
                        }
                    )
                }

                is WeatherUiState.Error -> {

                    Text(
                        text = state.message,

                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherCardModern(
    state: WeatherUiState.Success,
    onCreateReport: () -> Unit
) {

    Card(
        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(26.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    Color(0xFF2B2B2B)
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = state.city,

                        color = Color.White,

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Text(
                        text = state.condition,

                        color =
                            Color.LightGray,

                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            color = Olive,

                            shape =
                                RoundedCornerShape(
                                    20.dp
                                )
                        )
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        )
                ) {

                    Text(
                        text =
                            state.temperature,

                        color = Color.Black,

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )
                }
            }

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                MetricItem(
                    title = "Humidity",
                    value = state.humidity,
                    color = HumidityColor,
                    modifier =
                        Modifier.weight(1f)
                )

                MetricItem(
                    title = "Wind",
                    value = state.windSpeed,
                    color = WindColor,
                    modifier =
                        Modifier.weight(1f)
                )

                MetricItem(
                    title = "Pressure",
                    value = state.pressure,
                    color = PressureColor,
                    modifier =
                        Modifier.weight(1f)
                )
            }

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White.copy(
                                alpha = 0.05f
                            )
                    ),

                shape =
                    RoundedCornerShape(16.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text =
                            "Report readiness",

                        color =
                            Color.White,

                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )

                    Text(
                        text =
                            "Camera + DB ready",

                        color = Olive,

                        style =
                            MaterialTheme
                                .typography
                                .bodySmall
                    )
                }
            }

            Button(
                onClick =
                    onCreateReport,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            Olive,

                        contentColor =
                            Color.Black
                    ),

                shape =
                    RoundedCornerShape(24.dp)
            ) {

                Text(
                    text = "Create Report"
                )
            }
        }
    }
}

@Composable
fun MetricItem(
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,

        shape =
            RoundedCornerShape(16.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    Color(0xFF373737)
            )
    ) {

        Column(
            modifier =
                Modifier.padding(12.dp)
        ) {

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
}