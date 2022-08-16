package com.example.finalproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.location.Location

class MainViewModel : ViewModel() {

    private val _location = MutableLiveData<Location>()

    val location : LiveData<Location> = _location

    fun setLocation(location : Location)
    {
        _location.postValue(location)
    }
}