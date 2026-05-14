package com.project.weathersnap.database



import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var database:
            WeatherDatabase? = null

    fun getDatabase(
        context: Context
    ): WeatherDatabase {

        return database ?: synchronized(this) {

            val instance =
                Room.databaseBuilder(

                    context.applicationContext,

                    WeatherDatabase::class.java,

                    "weather_database"
                )
                    .build()

            database = instance

            instance
        }
    }
}