package com.gl4.tp5

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import weatherForecastResponse.WeatherForecastResponse

class WeatherViewModel(city: String): ViewModel() {
    private val weatherMutable = MutableLiveData<WeatherResponse>();
    val weather : LiveData<WeatherResponse> = weatherMutable;
    private val allWeathersMutable = MutableLiveData<WeatherForecastResponse>();
    val allWeathers : LiveData<WeatherForecastResponse> = allWeathersMutable;
    var city: String = "paris";

    init {
        getWeather(city);
    }

    fun searchByCity(city: String){
        getWeather(city);
    }

    fun searchForecast(city: String){
        getAllWeathers(city);
    }


    private fun getWeather(city: String = "paris" ) {
        RetrofitHelper.retrofitService.getWeather(city).enqueue(object: Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ){
                if(response.isSuccessful)
                    weatherMutable.value= response.body() as WeatherResponse
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
    private fun getAllWeathers(city: String = "paris" ) {
        RetrofitHelper.retrofitService.getAllWeathers(city).enqueue(object: Callback<WeatherForecastResponse> {
            override fun onResponse(
                call: Call<WeatherForecastResponse>,
                response: Response<WeatherForecastResponse>
            ){
                if(response.isSuccessful)
                    allWeathersMutable.value= response.body() as WeatherForecastResponse
            }

            override fun onFailure(call: Call<WeatherForecastResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }

}