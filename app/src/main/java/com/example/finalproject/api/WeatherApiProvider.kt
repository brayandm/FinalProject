package com.example.finalproject.api

import android.util.Log
import com.example.finalproject.MainActivity
import com.example.finalproject.model.WeatherItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.weatherbit.io/"

private const val TAG = "WeatherApiProvider"

class WeatherApiProvider {

    private val retrofit by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create<WeatherApiClient>()
    }

    fun fetchWeatherInfo(context: MainActivity, lat: Double, lon: Double) {

        retrofit.fetchWeatherInfo(lat, lon).enqueue(object : Callback<WeatherItem> {

            override fun onResponse(
                call: Call<WeatherItem>,
                response: Response<WeatherItem>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "Response: ${response.body()}")
                    context.viewModel.setWeatherItem(response.body()!!)
                    context.viewModel.setIsWeatherLoad(true)
                } else {
                    Log.e(TAG, "Error Fetching WeatherItem")
                }
            }

            override fun onFailure(call: Call<WeatherItem>, t: Throwable) {
                Log.e(TAG, "Error Fetching WeatherItem", t)
            }
        })
    }

    fun fetchWeatherInfoCity(context: MainActivity, city: String, country : String) {

        retrofit.fetchWeatherInfoCity(city, country).enqueue(object : Callback<WeatherItem> {

            override fun onResponse(
                call: Call<WeatherItem>,
                response: Response<WeatherItem>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "Response: ${response.body()}")
                    context.viewModel.setWeatherItem(response.body()!!)
                    context.viewModel.setIsWeatherLoad(true)
                    context.viewModel.setIsLocationCityLoad(true)
                } else {
                    Log.e(TAG, "Error Fetching WeatherItem")
                }
            }

            override fun onFailure(call: Call<WeatherItem>, t: Throwable) {
                Log.e(TAG, "Error Fetching WeatherItem", t)
            }
        })
    }
}