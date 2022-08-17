package com.example.finalproject

import android.app.Application
import com.example.finalproject.repository.AppDatabase
import com.example.finalproject.repository.CityFavoriteRepository


class AppApplication: Application()
{
    companion object {
        var appPreferences: AppPreferences? = null
        lateinit var instance: AppApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        appPreferences = AppPreferences(applicationContext)
    }

    val database by lazy { AppDatabase.getDatabase(this) }

    val repository by lazy { CityFavoriteRepository(database.cityFavoriteDao()) }
}