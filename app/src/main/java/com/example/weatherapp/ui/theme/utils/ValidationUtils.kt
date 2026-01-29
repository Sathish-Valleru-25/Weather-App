package com.example.weatherapp.ui.theme.utils

/**
 * This function will check if the input city name is valid.
 */
fun isValidInput(cityName: String): Boolean {
    return  cityName.isNotBlank()
}