package com.example.finalproject.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.finalproject.model.CityFavorite

@Dao
interface CityFavoriteDao {

    @Query("SELECT * FROM CityFavorite")
    fun getItems(): LiveData<List<CityFavorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: CityFavorite)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItems(items: List<CityFavorite>)

    @Delete
    fun deleteItem(item: CityFavorite)

    @Query("DELETE FROM CityFavorite")
    fun deleteAll()
}