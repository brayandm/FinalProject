package com.example.finalproject

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity

val appPreferences: AppPreferences by lazy {
    App.appPreferences!!
}

class App: Application()
{
    companion object {
        var appPreferences: AppPreferences? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        appPreferences = AppPreferences(applicationContext)
    }
}

class AppPreferences(context: Context) {

    private val preferences = context.getSharedPreferences("", Context.MODE_PRIVATE)

    fun setDarkMode(value: Boolean) {
        with (preferences.edit()) {
            putBoolean("DarkMode", value)
            apply()
        }
    }

    fun setFahrenheit(value: Boolean) {
        with (preferences.edit()) {
            putBoolean("Fahrenheit", value)
            apply()
        }
    }

    fun getDarkMode(): Boolean {
        return preferences.getBoolean("DarkMode", false)
    }

    fun getFahrenheit(): Boolean {
        return preferences.getBoolean("Fahrenheit", false)
    }
}