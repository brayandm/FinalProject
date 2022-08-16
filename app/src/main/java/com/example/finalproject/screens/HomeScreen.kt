package com.example.finalproject.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.MainActivity
import com.example.finalproject.MainViewModel
import com.example.finalproject.R
import com.example.finalproject.api.WeatherApiProvider
import com.example.finalproject.location.Location
import com.example.finalproject.location.fetchLocation
import com.example.finalproject.model.Data
import com.example.finalproject.model.WeatherItem
import com.example.finalproject.navigation.BottomNavigationScreens
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun HomeScreen(context: MainActivity) {

    fetchLocation(context, context.fusedLocationProviderClient)

    val location = context.viewModel.location.observeAsState(Location(.0,.0))
    val isWeatherLoad = context.viewModel.isWeatherLoad.observeAsState(false)

    Column() {
        Text(text = "Home Screen")
        Text(location.value.latitude.toString())
        Text(location.value.longitude.toString())

        val provider = WeatherApiProvider()
        provider.fetchWeatherInfo(context, location.value.latitude, location.value.longitude)

        if(isWeatherLoad.value)
        {
            val weatherItem = context.viewModel.weatherItem.observeAsState()

            Text(text = weatherItem.value?.data?.last()?.city_name.toString())

            val icon = weatherItem.value?.data?.last()?.weather?.icon

            var drawableResourceId = context.resources.getIdentifier(icon, "drawable", "com.example.finalproject")
            
            Image(painter = painterResource(id = drawableResourceId), contentDescription = "Weather Icon")
        }
    }
}