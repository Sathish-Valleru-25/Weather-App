package com.example.weatherapp.data.local

import androidx.room.Dao

import androidx.room.Query
import androidx.room.Upsert
import com.example.weatherapp.data.model.WeatherInfoResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherInfoDao {

    /**
     * Upsert will INSERT a new response if the table is empty,
     * or UPDATE the existing one since we use a fixed primary key (fixedId=1).
     */
    @Upsert
    suspend fun upsertWeatherInfo(weatherInfo: WeatherInfoResponse)

    /**
     * Gets the last saved weather response. We use Flow so the UI can automatically
     * observe changes.
     */
    @Query("SELECT * FROM weather_info WHERE fixedId = 0")
    fun getLastWeatherInfo(): Flow<WeatherInfoResponse?>

}
    