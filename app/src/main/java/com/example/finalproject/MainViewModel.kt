package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.location.Location
import com.example.finalproject.model.WeatherItem

class MainViewModel : ViewModel() {

    private val _location = MutableLiveData<Location>()
    private val _weatherItem = MutableLiveData<WeatherItem>()
    private val _isWeatherLoad = MutableLiveData<Boolean>()

    val location : LiveData<Location> = _location
    val weatherItem : LiveData<WeatherItem> = _weatherItem
    val isWeatherLoad : LiveData<Boolean> = _isWeatherLoad

    fun setLocation(location : Location)
    {
        _location.postValue(location)
    }

    fun setWeatherItem(weatherItem : WeatherItem)
    {
        _weatherItem.postValue(weatherItem)
    }

    fun setIsWeatherLoad(isWeatherLoad : Boolean)
    {
        _isWeatherLoad.postValue(isWeatherLoad)
    }
}