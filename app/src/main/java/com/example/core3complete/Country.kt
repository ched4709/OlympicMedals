package com.example.core3complete

data class Country(
    val country: String,
    val ioc: String,
    val timesCompeted: Int,
    val gold: Int,
    val silver: Int,
    val bronze: Int,
    val totalMedals: Int
) {
}
