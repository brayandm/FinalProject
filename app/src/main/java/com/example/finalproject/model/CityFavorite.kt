package com.example.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CityFavorite")
class CityFavorite(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    @ColumnInfo(name = "cityName")
    val cityName: String,
    @ColumnInfo(name = "countryName")
    val countryName: String,
)