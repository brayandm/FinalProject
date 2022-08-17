package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.location.Location
import com.example.finalproject.model.WeatherItem
import com.example.finalproject.navigation.BottomNavigationScreens
import java.util.*

class MainViewModel : ViewModel() {

    private val _location = MutableLiveData<Location>(Location(.0,.0))
    private val _weatherItem = MutableLiveData<WeatherItem>()
    private val _isWeatherLoad = MutableLiveData(false)
    private val _indexBottomNavigation = MutableLiveData(0)
    private val _navigationStack = MutableLiveData(Stack<BottomNavigationScreens>())


    val location : LiveData<Location> = _location
    val weatherItem : LiveData<WeatherItem> = _weatherItem
    val isWeatherLoad : LiveData<Boolean> = _isWeatherLoad
    val indexBottomNavigation : LiveData<Int> = _indexBottomNavigation
    val navigationStack : LiveData<Stack<BottomNavigationScreens>> = _navigationStack


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

    fun setIndexBottomNavigation(indexBottomNavigation : Int)
    {
        _indexBottomNavigation.postValue(indexBottomNavigation)
    }
}