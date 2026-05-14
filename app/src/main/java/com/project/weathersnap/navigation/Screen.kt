package com.project.weathersnap.navigation

import android.health.connect.datatypes.units.Temperature
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


sealed class Screen(val route: String) {

    data object Weather : Screen("weather")

    data object CreateReport :
        Screen(

            "create_report/" +
                    "{city}/" +
                    "{temperature}/" +
                    "{condition}/" +
                    "{humidity}/" +
                    "{wind}/" +
                    "{pressure}"
        ) {

        fun createRoute(

            city: String,

            temperature: String,

            condition: String,

            humidity: String,

            wind: String,

            pressure: String

        ): String {

            return "create_report/" +

                    URLEncoder.encode(
                        city,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        temperature,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        condition,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        humidity,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        wind,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        pressure,
                        StandardCharsets.UTF_8.toString()
                    )
        }
    }

    data object Camera :
        Screen(

            "camera/" +
                    "{city}/" +
                    "{temperature}/" +
                    "{condition}/" +
                    "{humidity}/" +
                    "{wind}/" +
                    "{pressure}"
        ) {

        fun createRoute(

            city: String,

            temperature: String,

            condition: String,

            humidity: String,

            wind: String,

            pressure: String

        ): String {

            return "camera/" +

                    URLEncoder.encode(
                        city,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        temperature,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        condition,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        humidity,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        wind,
                        StandardCharsets.UTF_8.toString()
                    ) + "/" +

                    URLEncoder.encode(
                        pressure,
                        StandardCharsets.UTF_8.toString()
                    )
        }
    }
    data object SavedReports : Screen("saved_reports")
}