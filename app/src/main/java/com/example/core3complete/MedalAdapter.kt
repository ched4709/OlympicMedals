package com.example.core3complete

import android.content.Context
import android.graphics.Color.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MedalAdapter(private val medals: MutableList<Country>,
                   private val listener: (Int) -> Unit): RecyclerView.Adapter<MedalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedalAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.medal_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount() = medals.size

    override fun onBindViewHolder(holder: MedalAdapter.ViewHolder, position: Int) {
        val country = medals[position]
        holder.bind(country)
    }

    inner class ViewHolder(private val v: View): RecyclerView.ViewHolder(v) {
        private val country: TextView = v.findViewById(R.id.textCountry)
        private val ioc: TextView = v.findViewById(R.id.textIOC)
        private val totalMedals: TextView = v.findViewById(R.id.textMedals)

        fun bind(medals: Country) {
            country.text = medals.country
            ioc.text = medals.ioc
            totalMedals.text = medals.totalMedals.toString()
            v.setOnClickListener {
                val toast = Toast.makeText(it.context, "${medals.country} has won ${medals.gold} gold medals.", Toast.LENGTH_SHORT)
                toast.show()

                Log.i("LIFECYCLE", "${medals.country} -- ${medals.ioc}")
                val sharedPref = it.context.getSharedPreferences("file", Context.MODE_PRIVATE) ?: return@setOnClickListener
                with (sharedPref.edit()) {
                    putString("country", country.text as String)
                    putString("ioc", ioc.text.toString())
                    apply()
                }
            }

            if(medals.totalMedals > 50)
            {
                itemView.setBackgroundColor(parseColor("#C5FF8F"))
            }
        }
    }
}