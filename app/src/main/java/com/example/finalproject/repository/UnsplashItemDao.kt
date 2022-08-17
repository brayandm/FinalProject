package com.example.finalproject.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalproject.model.CityFavorite

@Dao
interface UnsplashItemDao {

    @Query("SELECT * FROM UnsplashItem")
    fun getItems(): LiveData<List<CityFavorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: CityFavorite)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItems(items: List<CityFavorite>)
}