package com.example.weatherapp.data.repos

import android.util.Log
import com.example.weatherapp.data.local.WeatherInfoDao
import com.example.weatherapp.data.model.WeatherInfoResponse
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.di.ApiKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository implementation for fetching WeatherInfo data.
 */
class WeatherInfoRepositoryImpl  @Inject constructor(
    private val apiService: WeatherApiService,
    private val weatherInfoDao: WeatherInfoDao,
    @param:ApiKey private val apiKey: String
) : WeatherInfoApiRepository {

    // Function to fetch weather information for a given city from remote api
    override fun getWeatherInfo(city: String): Flow<Result<WeatherInfoResponse?>> = flow {
        try {
            val  response = apiService.getWeatherInfo(city,apiKey)

            if (response.isSuccessful) {

               response.body()?.let {
                   weatherInfoDao.upsertWeatherInfo(it)
               }
                emit(Result.success(response.body()))

            } else {
                emit(Result.failure(Exception("API Error: ${response.message()}")))
                Log.d("WeatherInfoRepositoryImpl", "Weather API failed  $city code : ${response.code()}, message: ${response.message()}")
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
            Log.d("WeatherInfoRepositoryImpl", "API fetch failed $city ${e.message}")
        }
    }

    // Function to get the last cached weather information.
    override fun getCachedWeatherInfo(): Flow<WeatherInfoResponse?> {
        return weatherInfoDao.getLastWeatherInfo()
    }
}