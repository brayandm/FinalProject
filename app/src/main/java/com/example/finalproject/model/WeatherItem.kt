package com.example.finalproject.model

data class WeatherItem(
    val count: Int?,
    val `data`: List<Data>?,
    val minutely: List<Any>?
)