package com.gl4.tp5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Observer
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: WeatherViewModel;

    lateinit var spinner : Spinner;
    lateinit var hum : TextView;
    lateinit var press : TextView;
    lateinit var temp : TextView;
    lateinit var desc : TextView;
    lateinit var tun : TextView;

    lateinit var btn : Button;
    var city: String = "tunis";

    var villes = listOf<String>("tunis","paris", "london")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = WeatherViewModel("paris")
        spinner = findViewById(R.id.spinner);
        btn = findViewById(R.id.button);
        hum = findViewById(R.id.humval)
        press = findViewById(R.id.pressval)
        temp = findViewById(R.id.temp)
        desc = findViewById(R.id.desc)
        tun = findViewById(R.id.tun)

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,villes)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //viewModel.searchByCity(resources.getStringArray(R.array.city)[position]);
                //city = resources.getStringArray(R.array.city)[position];
                city = villes[position]
                viewModel.searchByCity(villes[position])
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        viewModel.weather.observe(this,  { t: WeatherResponse ->
            if(t != null) {
                hum.setText(t.main.humidity.toString());
                press.setText(t.main.pressure.toString()) ;
                temp.setText((t.main.temp - 273.15).roundToInt().toString()  + "°C");
                desc.setText(t.weather[0].description);
                tun.setText(t.name) ;
            }
        })

        btn.setOnClickListener(){
            val intent = Intent(this, WeatherForecast::class.java);
            intent.putExtra("city", city);
            startActivity(intent);
        }
    }
}