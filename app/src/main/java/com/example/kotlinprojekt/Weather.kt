package com.example.kotlinprojekt

import com.google.gson.annotations.SerializedName

class Weather(
    @SerializedName("weather_state_abbr")
    val code: String,
    @SerializedName("min_temp")
    val minTemp: Double,
    @SerializedName("max_temp")
    val maxTemp: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_direction")
    val windDirection: Double,
    @SerializedName("air_pressure")
    val airPressure: Double
)