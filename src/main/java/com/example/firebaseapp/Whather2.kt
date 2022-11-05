package com.example.firebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class Whather2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whather2)

        lateinit var WhatherBtn: Button

        var tvLoggedInUserName : TextView
        WhatherBtn = findViewById(R.id.goToWeatherBtn)
        tvLoggedInUserName = this.findViewById(R.id.tvLoggedInUserName)

        var intent = intent
        var name = intent.getStringExtra("Username")

        tvLoggedInUserName.setText("Welcome" + name)

        WhatherBtn.setOnClickListener() {
            Log.d("TAG", "clicked")
            val intent = Intent(this, Wheater::class.java)
        }
    }
}