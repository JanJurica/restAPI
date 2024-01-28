package com.example.restapi


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity



class WallpaperActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

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



}