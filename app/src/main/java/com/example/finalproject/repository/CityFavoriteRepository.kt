package com.example.finalproject.repository

import androidx.lifecycle.LiveData
import com.example.finalproject.model.CityFavorite

class CityFavoriteRepository(private val cityFavoriteDao: CityFavoriteDao) {

    val allItems: LiveData<List<CityFavorite>> = cityFavoriteDao.getItems()

    fun insertItem(item: CityFavorite) {
        AppDatabase.databaseWriteExecutor.execute {
            cityFavoriteDao.insertItem(item)
        }
    }

    fun insertItems(items: List<CityFavorite>) {
        AppDatabase.databaseWriteExecutor.execute {
            cityFavoriteDao.insertItems(items)
        }
    }
}