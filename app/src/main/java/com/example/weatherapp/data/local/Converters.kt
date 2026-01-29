package com.example.weatherapp.data.local

import androidx.room.TypeConverter
import com.example.weatherapp.data.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    private val gson = Gson()

    // Converter for List<Weather>
    @TypeConverter
    fun fromWeatherList(weather: List<Weather>?): String? {
        return gson.toJson(weather)
    }

    @TypeConverter
    fun toWeatherList(weatherString: String?): List<Weather>? {
        if (weatherString == null) return null
        val listType = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(weatherString, listType)
    }

    // Converter for Main
    @TypeConverter
    fun fromMain(main: Main?): String? {
        return gson.toJson(main)
    }

    @TypeConverter
    fun toMain(mainString: String?): Main? {
        return gson.fromJson(mainString, Main::class.java)
    }

    // Converter for Wind
    @TypeConverter
    fun fromWind(wind: Wind?): String? {
        return gson.toJson(wind)
    }

    @TypeConverter
    fun toWind(windString: String?): Wind? {
        return gson.fromJson(windString, Wind::class.java)
    }

    // Converter for Clouds
    @TypeConverter
    fun fromClouds(clouds: Clouds?): String? {
        return gson.toJson(clouds)
    }

    @TypeConverter
    fun toClouds(cloudsString: String?): Clouds? {
        return gson.fromJson(cloudsString, Clouds::class.java)
    }

    // Converter for Sys
    @TypeConverter
    fun fromSys(sys: Sys?): String? {
        return gson.toJson(sys)
    }

    @TypeConverter
    fun toSys(sysString: String?): Sys? {
        return gson.fromJson(sysString, Sys::class.java)
    }

    // Converter for Coord
    @TypeConverter
    fun fromCoord(coord: Coord?): String? {
        return gson.toJson(coord)
    }

    @TypeConverter
    fun toCoord(coordString: String?): Coord? {
        return gson.fromJson(coordString, Coord::class.java)
    }
}
