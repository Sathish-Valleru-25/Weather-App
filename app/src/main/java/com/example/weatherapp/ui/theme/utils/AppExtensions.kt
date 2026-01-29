package com.example.weatherapp.ui.theme.utils

import android.icu.util.TimeZone
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


/**
 * Extension function to convert Kelvin temperature to Fahrenheit.
 */
 fun Double.toFahrenheit(): String {
    val fahrenheit = (this - 273.15) * 9/5 + 32
    return String.format("%.2f", fahrenheit)
}

/**
 * Extension function to convert Unix timestamp to a formatted time string.
 */
 fun Long.toFormattedTime(): String {

    val timezone = TimeZone.getDefault().id
    val instant = Instant.ofEpochSecond(this)
    val localDateTime = instant.atZone(ZoneId.of(timezone)).toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
    return localDateTime.format(formatter)
}