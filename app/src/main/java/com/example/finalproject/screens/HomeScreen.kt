package com.example.finalproject.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxSize()) {

        val provider = WeatherApiProvider()
        provider.fetchWeatherInfo(context, location.value.latitude, location.value.longitude)

        if(isWeatherLoad.value)
        {
            val weatherItem = context.viewModel.weatherItem.observeAsState()

            val weatherData = weatherItem.value?.data?.last()!!

            Text(text = weatherData.city_name.toString(), fontSize = 60.sp, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.padding(10.dp))

            Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {

                val drawableResourceId = context.resources.getIdentifier(weatherData.weather?.icon, "drawable",
                    "com.example.finalproject")

                Image(painter = painterResource(id = drawableResourceId), contentDescription = "Weather Icon",
                    modifier = Modifier.size(200.dp))

                Spacer(modifier = Modifier.padding(10.dp))


                Row() {
                    Text(text = weatherData.temp?.toInt().toString(), fontSize = 50.sp)
                    Text(text = "°C", fontSize = 30.sp,)
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = weatherData.weather?.description!!, fontSize = 30.sp)
        }
    }
}