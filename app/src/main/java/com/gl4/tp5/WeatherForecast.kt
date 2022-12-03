package com.gl4.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import weatherForecastResponse.CustomAdapter
import weatherForecastResponse.WeatherForecastResponse
import weatherForecastResponse.WeatherListElement

class WeatherForecast : AppCompatActivity() {
    lateinit var viewModel: WeatherViewModel;
    lateinit var recycler : RecyclerView;
    lateinit var list : ArrayList<WeatherListElement>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        var city = intent.getStringExtra("city") as String;
        viewModel = WeatherViewModel(city);
        recycler = findViewById(R.id.recycler);
        recycler.layoutManager = LinearLayoutManager(applicationContext);
        viewModel.searchForecast(city);
        list = ArrayList<WeatherListElement>()
        val adapter = CustomAdapter(list);
        recycler.adapter = adapter;
        viewModel.allWeathers.observe(this, {t:WeatherForecastResponse ->
            if(t != null){
                list = t.list as ArrayList<WeatherListElement>
                val adapter = CustomAdapter(list)
                recycler.adapter = adapter;
            }
        })
    }
}