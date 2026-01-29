package com.example.weatherapp.data.repos
import com.example.weatherapp.data.model.WeatherInfoResponse
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for fetching WeatherInfo data.
 */
interface WeatherInfoApiRepository {

    // Function to fetch weather information for a given city from remote api
    fun getWeatherInfo(city: String): Flow<Result<WeatherInfoResponse?>>

    // Function to get the last cached weather information
    fun getCachedWeatherInfo(): Flow<WeatherInfoResponse?>
}