package com.gl4.tp5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import weatherForecastResponse.WeatherForecastResponse

interface WeatherAPI {

    @GET("weather?APPID=17db59488cadcad345211c36304a9266")
    fun getWeather(@Query("q") city : String) : Call<WeatherResponse>

    @GET("forecast/daily?APPID=17db59488cadcad345211c36304a9266")
    fun getAllWeathers(@Query("q") city : String) : Call<WeatherForecastResponse>
}
