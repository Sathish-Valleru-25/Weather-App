package com.example.weatherapp.ui.theme

import com.example.weatherapp.data.model.WeatherInfoResponse


/**
 * Sealed class representing the different states of the weather information UI.
 */
sealed class WeatherUiState {

    data object Idle : WeatherUiState()
    data object Loading : WeatherUiState()
    data class Success(val weatherInfoResponse: WeatherInfoResponse?) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}
