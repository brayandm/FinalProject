package com.example.finalproject

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity

const val KEY_OPTION = "DarkMode"
const val DEFAULT_OPTION_VALUE = false

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
            putBoolean(KEY_OPTION, value)
            apply()
        }
    }

    fun getDarkMode(): Boolean {
        return preferences.getBoolean(KEY_OPTION, DEFAULT_OPTION_VALUE)
    }
}