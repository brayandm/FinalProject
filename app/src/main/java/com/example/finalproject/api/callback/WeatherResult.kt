package com.example.finalproject.api.callback

import com.example.finalproject.model.WeatherItem

interface UnsplashResult {

    fun onDataFetchedSuccess(images: WeatherItem)

    fun onDataFetchedFailed()
}