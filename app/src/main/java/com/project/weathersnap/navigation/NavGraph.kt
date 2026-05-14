package com.project.weathersnap.navigation


import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.weathersnap.ui.camera.CameraScreen
import com.project.weathersnap.ui.report.CreateReportScreen
import com.project.weathersnap.ui.saved.SavedReportsScreen
import com.project.weathersnap.ui.weather.WeatherScreen
import com.project.weathersnap.viewmodel.ReportSharedViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val application =
        LocalContext.current
            .applicationContext as Application

    val sharedViewModel:
            ReportSharedViewModel = viewModel(

        factory = object :
            ViewModelProvider.Factory {

            override fun <T : ViewModel>
                    create(
                modelClass: Class<T>
            ): T {

                return ReportSharedViewModel(
                    application
                ) as T
            }
        }
    )

    NavHost(
        navController = navController,
        startDestination = Screen.Weather.route
    ) {

        composable(Screen.Weather.route) {
            WeatherScreen(navController=navController)
        }

        composable(
            route =
                Screen.CreateReport.route
        ) { backStackEntry ->

            val city =
                URLDecoder.decode(

                    backStackEntry.arguments
                        ?.getString("city")
                        ?: "",

                    StandardCharsets.UTF_8.toString()
                )

            val temperature =
                backStackEntry.arguments
                    ?.getString("temperature")
                    ?: ""

            val condition =
                URLDecoder.decode(

                    backStackEntry.arguments
                        ?.getString("condition")
                        ?: "",

                    StandardCharsets.UTF_8.toString()
                )

            val humidity =
                backStackEntry.arguments
                    ?.getString("humidity")
                    ?: ""

            val wind =
                URLDecoder.decode(

                    backStackEntry.arguments
                        ?.getString("wind")
                        ?: "",

                    StandardCharsets.UTF_8.toString()
                )

            val pressure =
                URLDecoder.decode(

                    backStackEntry.arguments
                        ?.getString("pressure")
                        ?: "",

                    StandardCharsets.UTF_8.toString()
                )

            CreateReportScreen(navController=navController,
                city = city,
                temperature = temperature,
                condition = condition,
                sharedViewModel=sharedViewModel,
                humidity = humidity,
                wind = wind,
                pressure = pressure
            )
        }
        composable(
            route = Screen.Camera.route
        ) { backStackEntry ->

            val city =
                backStackEntry.arguments
                    ?.getString("city")
                    ?: ""

            val temperature =
                backStackEntry.arguments
                    ?.getString("temperature")
                    ?: ""

            val condition =
                backStackEntry.arguments
                    ?.getString("condition")
                    ?: ""

            val humidity =
                backStackEntry.arguments
                    ?.getString("humidity")
                    ?: ""

            val wind =
                backStackEntry.arguments
                    ?.getString("wind")
                    ?: ""

            val pressure =
                backStackEntry.arguments
                    ?.getString("pressure")
                    ?: ""

            CameraScreen(

                navController = navController,

                city = city,

                temperature = temperature,

                condition = condition,

                humidity = humidity,

                wind = wind,

                pressure = pressure,

                sharedViewModel =
                    sharedViewModel
            )
        }
        composable(Screen.SavedReports.route) {
            SavedReportsScreen(
                sharedViewModel = sharedViewModel, navController = navController
            )
        }
    }
}