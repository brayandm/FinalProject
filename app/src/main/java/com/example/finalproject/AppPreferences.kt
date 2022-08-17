package com.example.finalproject

import android.content.Context
import androidx.activity.ComponentActivity

const val KEY_OPTION = "DarkMode"
const val DEFAULT_OPTION_VALUE = false

class AppPreferences(context: ComponentActivity) {

    private val preferences = context.getPreferences(Context.MODE_PRIVATE)

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