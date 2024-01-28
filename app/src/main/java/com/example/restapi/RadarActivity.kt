package com.example.restapi

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Povolit tlačítko zpět
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Zavolá metodu, která simuluje stisk tlačítka zpět
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun fnc() {
        RetroFitConfig.instance.getWeatherData("London", "85362409f33bd995b8758934438d481b")
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        // Handle the successful response here
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    // Handle failure
                }
            })
    }

}