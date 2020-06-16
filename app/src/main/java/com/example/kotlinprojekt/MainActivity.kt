package com.example.kotlinprojekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kotlinprojekt.R.layout.activity_main
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    fun loadData(){

        viewModel.isProgress.observe(this, Observer { show ->
            progressView.visibility = if (show)
                View.VISIBLE
            else
                View.GONE
        })

        viewModel.isProgress.observe(this, Observer {
            showProgress(it)
        })

        viewModel.response.observe(this, Observer {
            showResult(it)
        })

        viewModel.error.observe(this, Observer {
            if (it == null)
                return@Observer

            showError(it)
            viewModel.error.value = null
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        loadData()

    }


    private fun showError(error: Throwable) {
        containerView.visibility = View.GONE
        Snackbar.make(
            mainView,
            "Error: ${error.message}",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showProgress(show: Boolean) {
        progressView.visibility = if(show)
            View.VISIBLE
        else
            View.GONE
    }

    private fun showResult(model: ResponseModel){
        containerView.visibility = View.VISIBLE

        val weather = model.forecasts.first()

        val iconUrl = "https://www.metaweather.com/static/img/weather/png/${weather.code}.png"

        Glide.with(this)
            .load(iconUrl)
            .into(iconWeather)

        textLocation.text = model.title
        textTempMax.text = "${weather.maxTemp.roundToInt()}°"
        textTempMin.text = "${weather.minTemp.roundToInt()}°"
        textAirPressure.text = "${weather.airPressure.roundToInt()} hPa"
        textWindSpeed.text = "${(weather.windSpeed * 2.6).roundToInt()} km/h"
        iconWind.rotation = weather.windDirection.toFloat()
    }

    fun buttonClicked(view: View) {
        loadData()
    }
}




