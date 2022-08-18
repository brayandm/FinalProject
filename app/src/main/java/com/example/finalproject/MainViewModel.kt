package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.location.Location
import com.example.finalproject.location.LocationCity
import com.example.finalproject.model.CityFavorite
import com.example.finalproject.model.WeatherItem
import com.example.finalproject.navigation.BottomNavigationScreens
import com.example.finalproject.repository.CityFavoriteRepository
import java.util.*

class MainViewModel(private val repository: CityFavoriteRepository) : ViewModel(), ViewModelProvider.Factory {

    private val _location = MutableLiveData(Location(.0,.0))
    private val _locationCity = MutableLiveData(LocationCity("Barcelona","Spain"))
    private val _weatherItem = MutableLiveData<WeatherItem>()
    private val _isWeatherLoad = MutableLiveData(false)
    private val _isLocationLoad = MutableLiveData(false)
    private val _isLocationCityLoad = MutableLiveData(false)
    private val _indexBottomNavigation = MutableLiveData(0)
    private val _navigationStack = MutableLiveData(Stack<BottomNavigationScreens>())
    private val _isMyLocation = MutableLiveData(true)

    val location : LiveData<Location> = _location
    val locationCity : LiveData<LocationCity> = _locationCity
    val weatherItem : LiveData<WeatherItem> = _weatherItem
    val isWeatherLoad : LiveData<Boolean> = _isWeatherLoad
    val isLocationLoad : LiveData<Boolean> = _isLocationLoad
    val isLocationCityLoad : LiveData<Boolean> = _isLocationCityLoad
    val indexBottomNavigation : LiveData<Int> = _indexBottomNavigation
    val navigationStack : LiveData<Stack<BottomNavigationScreens>> = _navigationStack
    val isMyLocation : LiveData<Boolean> = _isMyLocation

    fun setLocation(location : Location)
    {
        _location.postValue(location)
    }

    fun setLocationCity(locationCity : LocationCity)
    {
        _locationCity.postValue(locationCity)
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

    fun setIsLocationCityLoad(isLocationCityLoad : Boolean)
    {
        _isLocationCityLoad.postValue(isLocationCityLoad)
    }

    fun setIndexBottomNavigation(indexBottomNavigation : Int)
    {
        _indexBottomNavigation.postValue(indexBottomNavigation)
    }

    fun setIsMyLocation(isMyLocation : Boolean)
    {
        _isMyLocation.postValue(isMyLocation)
    }

    fun getCityFavoritesFromDatabase(): LiveData<List<CityFavorite>>
    {
        return repository.allItems()
    }

    fun addCityFavorite(cityFavorite : CityFavorite)
    {
        repository.insertItem(cityFavorite)
    }

    fun deleteCityFavorite(cityFavorite : CityFavorite)
    {
        repository.deleteItem(cityFavorite)
    }

    fun deleteAll()
    {
        repository.deleteAll()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}