package com.example.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent;
import android.view.View;
import android.widget.TextView
import androidx.cardview.widget.CardView

class MenuActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val radarCard: CardView = findViewById(R.id.radarCard)
        val mapCard: CardView = findViewById(R.id.mapCard)
        val wallpaperCard: CardView = findViewById(R.id.wallpaperCard)
        val aboutCard: CardView = findViewById(R.id.aboutCard)

        radarCard.setOnClickListener {
            val intent = Intent(this, RadarActivity::class.java)
            startActivity(intent)
        }

        mapCard.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        wallpaperCard.setOnClickListener {
            val intent = Intent(this, WallpaperActivity::class.java)
            startActivity(intent)
        }

        aboutCard.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

}

