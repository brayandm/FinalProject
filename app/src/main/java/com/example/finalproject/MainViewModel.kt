package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.location.Location
import com.example.finalproject.model.CityFavorite
import com.example.finalproject.model.WeatherItem
import com.example.finalproject.navigation.BottomNavigationScreens
import com.example.finalproject.repository.CityFavoriteRepository
import java.util.*

class MainViewModel(private val repository: CityFavoriteRepository) : ViewModel(), ViewModelProvider.Factory {

    private val _location = MutableLiveData<Location>(Location(.0,.0))
    private val _weatherItem = MutableLiveData<WeatherItem>()
    private val _isWeatherLoad = MutableLiveData(false)
    private val _isLocationLoad = MutableLiveData(false)
    private val _indexBottomNavigation = MutableLiveData(0)
    private val _navigationStack = MutableLiveData(Stack<BottomNavigationScreens>())

    val location : LiveData<Location> = _location
    val weatherItem : LiveData<WeatherItem> = _weatherItem
    val isWeatherLoad : LiveData<Boolean> = _isWeatherLoad
    val isLocationLoad : LiveData<Boolean> = _isLocationLoad
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

    fun setIsLocationLoad(isLocationLoad : Boolean)
    {
        _isLocationLoad.postValue(isLocationLoad)
    }

    fun setIndexBottomNavigation(indexBottomNavigation : Int)
    {
        _indexBottomNavigation.postValue(indexBottomNavigation)
    }

    fun getCityFavoritesFromDatabase(): LiveData<List<CityFavorite>>
    {
        return repository.allItems()
    }

    fun addCityFavorite(cityFavorite : CityFavorite)
    {
        repository.insertItem(cityFavorite)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}