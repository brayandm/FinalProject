package com.example.finalproject.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.finalproject.model.CityFavorite

class CityFavoriteRepository(private val cityFavoriteDao: CityFavoriteDao) {

    fun allItems(): LiveData<List<CityFavorite>>
    {
        Log.d("Database", "Get")
        Log.d("Database", (cityFavoriteDao.getItems().value?:"null").toString())

        return cityFavoriteDao.getItems()
    }

    fun insertItem(item: CityFavorite) {
        AppDatabase.databaseWriteExecutor.execute {
            Log.d("Database", "Inserted")
            Log.d("Database", item.cityName)
            Log.d("Database", item.countryName)
            cityFavoriteDao.insertItem(item)
        }
    }

    fun deleteItem(item: CityFavorite) {
        AppDatabase.databaseWriteExecutor.execute {
            cityFavoriteDao.deleteItem(item)
        }
    }

    fun deleteAll()
    {
        AppDatabase.databaseWriteExecutor.execute {
            cityFavoriteDao.deleteAll()
        }
    }
}