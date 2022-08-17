package com.example.finalproject.repository

import androidx.lifecycle.LiveData
import com.example.finalproject.model.CityFavorite

class UnsplashItemRepository(private val unsplashDao: UnsplashItemDao) {

    val allItems: LiveData<List<CityFavorite>> = unsplashDao.getItems()

    fun insertItem(item: CityFavorite) {
        AppDatabase.databaseWriteExecutor.execute {
            unsplashDao.insertItem(item)
        }
    }

    fun insertItems(items: List<CityFavorite>) {
        AppDatabase.databaseWriteExecutor.execute {
            unsplashDao.insertItems(items)
        }
    }
}