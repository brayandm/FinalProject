package com.example.finalproject.api

import com.example.finalproject.model.WeatherItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val ACCESS_KEY = "bfdb799cb37447cc9efe230f487c3353"

interface WeatherApiClient {
    @GET("v2.0/current/")
    fun fetchWeatherInfo(@Query(value = "lat") lat : Double,
                      @Query(value = "lon") lon : Double,
                         @Query(value = "key") key : String = ACCESS_KEY): Call<WeatherItem>
    @GET("v2.0/current/")
    fun fetchWeatherInfoCity(@Query(value = "city") city : String,
                         @Query(value = "country") country : String,
                         @Query(value = "key") key : String = ACCESS_KEY): Call<WeatherItem>
}