package com.project.weathersnap.ui.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.project.weathersnap.navigation.Screen
import com.project.weathersnap.viewmodel.ReportSharedViewModel

private val Olive = Color(0xFFD7E88F)
private val DarkBg = Color(0xFF0B1207)
private val DarkCard = Color(0xFF242424)

private val HumidityColor =
    Color(0xFF5BC0A5)

private val WindColor =
    Color(0xFF6FA8FF)

private val PressureColor =
    Color(0xFFFFB870)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReportScreen(
    navController: NavController,
    city: String,
    temperature: String,
    condition: String,
    humidity: String,
    wind: String,
    pressure: String,
    sharedViewModel: ReportSharedViewModel
) {

    val notes = remember {
        mutableStateOf("")
    }

    val imageUri by
    sharedViewModel.imageUri.collectAsState()

    Scaffold(

        containerColor = DarkBg,

        topBar = {

            TopAppBar(

                title = {},

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ArrowBack,

                            contentDescription = null,

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBg)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(
                    rememberScrollState()
                ),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            // HEADER

            Card(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(95.dp),

                shape =
                    RoundedCornerShape(28.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = Olive
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "Create Report",

                            style =
                                MaterialTheme
                                    .typography
                                    .titleLarge,

                            color = Color.Black
                        )

                        Text(
                            text =
                                "Capture weather evidence",

                            style =
                                MaterialTheme
                                    .typography
                                    .bodySmall,

                            color =
                                Color.Black.copy(
                                    alpha = 0.7f
                                )
                        )
                    }

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },

                        colors =
                            ButtonDefaults
                                .buttonColors(
                                    containerColor =
                                        Color.Black
                                ),

                        shape =
                            RoundedCornerShape(50)
                    ) {

                        Text(
                            text = "Back",

                            color = Olive
                        )
                    }
                }
            }

            // WEATHER CARD

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(28.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = DarkCard
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(18.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(18.dp)
                ) {

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
                                text = city,

                                style =
                                    MaterialTheme
                                        .typography
                                        .titleLarge,

                                color = Color.White
                            )

                            Text(
                                text = condition,

                                style =
                                    MaterialTheme
                                        .typography
                                        .bodyMedium,

                                color =
                                    Color.LightGray
                            )
                        }

                        Box(
                            modifier =
                                Modifier
                                    .clip(
                                        RoundedCornerShape(
                                            22.dp
                                        )
                                    )
                                    .background(
                                        Color(0xFF556B12)
                                    )
                                    .padding(
                                        horizontal = 18.dp,
                                        vertical = 12.dp
                                    )
                        ) {

                            Text(
                                text = temperature,

                                style =
                                    MaterialTheme
                                        .typography
                                        .titleLarge,

                                color = Olive
                            )
                        }
                    }

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.spacedBy(10.dp)
                    ) {

                        WeatherInfoCard(

                            modifier =
                                Modifier.weight(1f),

                            title = "Humidity",

                            value = humidity,

                            valueColor =
                                HumidityColor
                        )
                        WeatherInfoCard(
                            modifier =
                                Modifier.weight(1f),

                            title = "Wind",

                            value = wind,

                            valueColor =
                                WindColor
                        )

                        WeatherInfoCard(
                            modifier =
                                Modifier.weight(1f),

                            title = "Pressure",

                            value = pressure,

                            valueColor =
                                PressureColor
                        )
                    }
                }
            }

            // PHOTO SECTION

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(28.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = DarkCard
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(18.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(18.dp)
                ) {

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clip(
                                    RoundedCornerShape(
                                        24.dp
                                    )
                                )
                                .background(

                                    Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFF4B4B40),
                                            Color(0xFF6B7A12)
                                        )
                                    )
                                ),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        if (imageUri != null) {

                            AsyncImage(
                                model = imageUri,

                                contentDescription = null,

                                modifier =
                                    Modifier.fillMaxSize(),

                                contentScale =
                                    ContentScale.Crop
                            )

                        } else {

                            Text(
                                text =
                                    "Photo Preview",

                                color = Color.White
                            )
                        }
                    }

                    Button(
                        onClick = {

                            navController.navigate(

                                Screen.Camera.createRoute(
                                    city = city,
                                    temperature = temperature,
                                    condition = condition,
                                    humidity=humidity,
                                    wind=wind,
                                    pressure=pressure
                                )
                            )
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp),

                        colors =
                            ButtonDefaults
                                .buttonColors(
                                    containerColor = Olive
                                ),

                        shape =
                            RoundedCornerShape(50)
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.CameraAlt,

                            contentDescription = null,

                            tint = Color.Black
                        )

                        Text(
                            text = " Capture Photo",

                            color = Color.Black
                        )
                    }
                }
            }

            // NOTES

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(28.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = DarkCard
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(18.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Field Notes",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium,

                        color = Color.White
                    )

                    OutlinedTextField(
                        value = notes.value,

                        onValueChange = {
                            notes.value = it
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(150.dp),

                        label = {
                            Text("Notes")
                        },

                        shape =
                            RoundedCornerShape(20.dp)
                    )
                }
            }

            // SAVE BUTTON

            Button(
                onClick = {

                    sharedViewModel.saveReport(

                        city = city,

                        temperature = temperature,

                        condition = condition,

                        humidity = humidity,

                        wind = wind,

                        pressure = pressure,

                        notes = notes.value

                    ) {

                        sharedViewModel
                            .clearReportState()

                        navController.navigate(
                            Screen.SavedReports.route
                        ) {

                            popUpTo(
                                Screen.Weather.route
                            )

                            launchSingleTop = true
                        }
                    }
                },

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .padding(bottom = 24.dp),

                colors =
                    ButtonDefaults
                        .buttonColors(
                            containerColor = Olive
                        ),

                shape =
                    RoundedCornerShape(50)
            ) {

                Text(
                    text = "Save Report",

                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun WeatherInfoCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    valueColor: Color
) {

    Card(
        modifier = modifier,

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    Color(0xFF34342F)
            )
    ) {

        Column(
            modifier =
                Modifier.padding(14.dp),

            verticalArrangement =
                Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = title,

               fontSize = 10.sp,

                color =
                    Color.LightGray
            )

            Text(
                text = value,

                fontSize = 10.sp,

                color = valueColor
            )
        }
    }
}