package com.example.weatherapp.data.remote


import com.example.weatherapp.data.model.WeatherInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for the Weather API.
 */
interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeatherInfo(
        @Query("q") city: String,
        @Query("appid") appId: String
    ): Response<WeatherInfoResponse>
}