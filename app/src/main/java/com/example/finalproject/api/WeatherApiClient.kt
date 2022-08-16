package com.example.finalproject.api

import com.example.finalproject.model.WeatherItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val AUTHORIZATION_CLIENT_ID = "Client-ID"
private const val ACCESS_KEY = "e5befbf826f546e4a1498a2226bb9b96"

interface WeatherApiClient {
    @GET("v2.0/current/")
    fun fetchWeatherInfo(@Query(value = "lat") lat : Double,
                      @Query(value = "lon") lon : Double,
                         @Query(value = "key") key : String = ACCESS_KEY): Call<WeatherItem>
}