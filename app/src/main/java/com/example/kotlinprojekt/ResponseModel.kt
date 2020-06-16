package com.example.kotlinprojekt

import com.google.gson.annotations.SerializedName

class ResponseModel(
    @SerializedName("title")
    val title: String,
    @SerializedName("consolidated_weather")
    val forecasts: List<Weather>
)
