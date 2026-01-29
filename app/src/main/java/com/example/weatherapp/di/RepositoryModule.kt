package com.example.weatherapp.di

import com.example.weatherapp.data.repos.WeatherInfoApiRepository
import com.example.weatherapp.data.repos.WeatherInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing dependencies related to the repository.
 */

@Module
@InstallIn(SingletonComponent :: class)
abstract  class RepositoryModule {

    @Binds
    @Singleton
    abstract  fun bindWeatherInfoRepository(weatherInfoRepositoryImpl: WeatherInfoRepositoryImpl): WeatherInfoApiRepository

}