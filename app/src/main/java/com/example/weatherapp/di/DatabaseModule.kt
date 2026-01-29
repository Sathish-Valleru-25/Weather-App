package com.example.weatherapp.di

import com.example.weatherapp.data.local.AppDatabase
import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.local.WeatherInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing dependencies related to the database.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideIpInfoDao(appDatabase: AppDatabase): WeatherInfoDao {
        return appDatabase.weatherInfoDao()
    }
}
