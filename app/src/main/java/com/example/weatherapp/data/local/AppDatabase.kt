package com.example.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.data.model.WeatherInfoResponse

@Database(
    entities = [WeatherInfoResponse::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides the DAO for accessing the last weather info response.
     */
    abstract fun weatherInfoDao(): WeatherInfoDao

}
