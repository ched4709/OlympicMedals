package com.example.core3complete

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val medalList = findViewById<RecyclerView>(R.id.medalList)
        val medals = mutableListOf<Country>()

        createList(medals)

        val adapter = MedalAdapter(medals) {}
        medalList.adapter = adapter
        medalList.layoutManager = LinearLayoutManager(this)
    }

    private fun createList(medals: MutableList<Country>) {
        resources.openRawResource(R.raw.medallists).bufferedReader()
            .forEachLine {
                val temp = it.split(",")
                if (temp[0] == "Country")
                    return@forEachLine
                medals.add(Country(temp[0], temp[1], temp[2].toInt(), temp[3].toInt(), temp[4].toInt(), temp[5].toInt(), temp[3].toInt() + temp[4].toInt() + temp[5].toInt()))
            }

        medals.forEach {
            Log.i("FILELINE", "${it.country} -- ${it.ioc} -- ${it.timesCompeted} -- ${it.bronze} -- ${it.silver} -- ${it.gold} -- ${it.totalMedals}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.countrymenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val sharedPref = this.getSharedPreferences("file", Context.MODE_PRIVATE)
        val countryPref = sharedPref.getString("country", "")
        val iocPref = sharedPref.getString("ioc", "")
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra("country", countryPref)
        i.putExtra("ioc", iocPref)
        startActivity(i)
        return true
    }
}
