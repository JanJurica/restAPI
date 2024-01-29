package com.example.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadarActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Povolit tlačítko zpět

        val cityEditText: EditText = findViewById(R.id.city)
        val confirmButton: Button = findViewById(R.id.confirmButton)
        val textViewCountry: TextView = findViewById(R.id.textViewCountry)

        confirmButton.setOnClickListener {
            val userInput = cityEditText.text.toString()
            if (userInput.isNotBlank()) {
                fnc(userInput)
            } else {
                // Uživatel nezadal žádné město, zpracujte chybu nebo poskytněte zpětnou vazbu
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }


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




    private fun fnc(cityName: String) {
        RetroFitConfig.instance.getWeatherData(cityName, "85362409f33bd995b8758934438d481b")
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()

                        Log.d("WeatherResponse", "Successful response: $weatherResponse")

                        val city = weatherResponse?.name
                        val country = weatherResponse?.sys?.country
                        val humidity = weatherResponse?.main?.humidity
                        val windSpeed = weatherResponse?.wind?.speed

                        findViewById<TextView>(R.id.textViewCountry).text = "Country: $country"
                        findViewById<TextView>(R.id.textViewHumidity).text = "Humidity: $humidity%"
                        findViewById<TextView>(R.id.textViewPressure).text = "Pressure: ${weatherResponse?.main?.pressure} hPa"
                        findViewById<TextView>(R.id.textViewWindSpeed).text = "Wind Speed: $windSpeed m/s"
                        findViewById<TextView>(R.id.textViewWeatherDescription).text = "Weather: ${weatherResponse?.weather?.firstOrNull()?.description}"
                    }
                    else {
                        // Zpracujte neúspěšnou odpověď zde
                        val errorBody = response.errorBody()?.string()
                        println("Error: $errorBody")
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    // Zpracujte chybu zde
                    println("Error: ${t.message}")
                }
            })
    }

    data class WeatherResponse(
        val name: String,
        val main: MainData,
        val sys: SysData,
        val wind: WindData,
        val weather: List<WeatherData>
    )
    data class WeatherData(
        val description: String
    )

    data class MainData(
        val temp: Double,
        val humidity: Int,
        val pressure: Double,
        val weather: String
    )

    data class SysData(
        val country: String
    )

    data class WindData(
        val speed: Double
    )



}