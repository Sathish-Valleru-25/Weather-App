package com.example.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom Application class for Hilt.
 * This class is annotated with @HiltAndroidApp to enable Hilt's dependency injection
 */
@HiltAndroidApp
class WeatherAppApplication: Application()