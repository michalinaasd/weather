package com.example.kotlinprojekt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject

class WeatherViewModel : ViewModel() {
    val isProgress = MutableLiveData(false)
    val response = MutableLiveData<ResponseModel>()
    val error = MutableLiveData<Throwable>()

    init{
        val url = "https://www.metaweather.com/api/location/523920/"
        isProgress.value = true
        Fuel.get(url)
            .responseObject<ResponseModel>{_, _, result ->
                isProgress.value = false
                result.fold({
                    response.value = it
                }, {
                    error.value = it.exception
                })
            }
    }
}