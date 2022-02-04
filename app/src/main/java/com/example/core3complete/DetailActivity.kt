package com.example.core3complete

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val countryInfo = findViewById<TextView>(R.id.countryInfo)
        val country = intent.getStringExtra("country")
        val ioc = intent.getStringExtra("ioc")

        countryInfo.text = "The country last clicked was $country ($ioc)."
    }
}