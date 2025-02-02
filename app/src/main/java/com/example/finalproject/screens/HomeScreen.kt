package com.example.finalproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.animation.LoadingAnimation
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.api.WeatherApiProvider
import com.example.finalproject.location.Location
import com.example.finalproject.location.LocationCity
import com.example.finalproject.location.fetchLocation
import com.example.finalproject.model.CityFavorite
import com.example.finalproject.navigation.BottomNavigationScreens
import com.example.finalproject.utils.Utils
import kotlin.math.round
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(context: MainActivity, navController: NavHostController, isDarkTheme: MutableState<Boolean>, isFahrenheit: MutableState<Boolean>) {

    fetchLocation(context, context.fusedLocationProviderClient)

    val location = context.viewModel.location.observeAsState(Location())
    val locationCity = context.viewModel.locationCity.observeAsState(LocationCity())
    val isWeatherLoad = context.viewModel.isWeatherLoad.observeAsState(false)
    val isWeatherCityLoad = context.viewModel.isWeatherCityLoad.observeAsState(false)
    val isLocationLoad = context.viewModel.isLocationLoad.observeAsState(false)
    val isLocationCityLoad = context.viewModel.isLocationCityLoad.observeAsState(false)
    val isMyLocation = context.viewModel.isMyLocation.observeAsState(true)

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {

            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Home", fontSize = 35.sp)

            Spacer(modifier = Modifier.padding(10.dp))

            Divider(thickness = 1.dp)
        }
        Spacer(modifier = Modifier.padding(10.dp))

        val provider = WeatherApiProvider()

        if(isMyLocation.value)
        {
            if(location.value.latitude != null && location.value.longitude != null)
            {
                provider.fetchWeatherInfo(context,
                    location.value.latitude!!, location.value.longitude!!)
            }
        }
        else
        {
            if(locationCity.value.city != null && locationCity.value.country != null) {
                provider.fetchWeatherInfoCity(
                    context,
                    locationCity.value.city!!,
                    locationCity.value.country!!
                )
            }
        }

        if((!isMyLocation.value && isLocationCityLoad.value && isWeatherCityLoad.value) ||
                    (isMyLocation.value && isLocationLoad.value &&
                            location.value.latitude != null && location.value.longitude != null && isWeatherLoad.value))
        {
            val weatherItem = context.viewModel.weatherItem.observeAsState()

            val weatherData = weatherItem.value?.data?.last()!!

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()) {


                Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                    Text(text = weatherData.city_name.toString(), fontSize = 50.sp,
                        textAlign = TextAlign.Center, modifier = Modifier.width(250.dp))

                    Text(text = "(" + weatherData.country_code.toString() + ")", fontSize = 25.sp,
                        textAlign = TextAlign.Center)

                    Spacer(modifier = Modifier.padding(10.dp))

                    FloatingActionButton(
                        onClick = {
                            context.viewModel.addCityFavorite(CityFavorite(
                                Utils().getID(weatherData.city_name.toString(),
                                    weatherData.country_code.toString()),
                                weatherData.city_name.toString(),
                                weatherData.country_code.toString()))
                            context.viewModel.setIndexBottomNavigation(2)
                            context.viewModel.navigationStack.value!!.push(BottomNavigationScreens.Home)
                            navController.navigate(BottomNavigationScreens.Favorites.route) },
                        content = {

                            Icon(painter = painterResource(R.drawable.ic_favourite),
                                contentDescription = "", modifier = Modifier
                                    .padding(20.dp)
                                    .size(20.dp))
                        },
                        backgroundColor = MaterialTheme.colors.primary,
                    )
                }


                Spacer(modifier = Modifier.padding(10.dp))

                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {

                    val drawableResourceId = context.resources.getIdentifier(weatherData.weather?.icon, "drawable",
                        "com.example.finalproject")

                    Image(painter = painterResource(id = drawableResourceId), contentDescription = "Weather Icon",
                        modifier = Modifier.size(200.dp))

                    Spacer(modifier = Modifier.padding(10.dp))


                    Row {
                        if(isFahrenheit.value)
                        {
                            Text(text = ((weatherData.temp ?: 0.0) * 9.0 / 5.0 + 32.0).roundToInt().toString(), fontSize = 50.sp)
                            Text(text = "°F", fontSize = 30.sp)
                        }
                        else
                        {
                            Text(text = weatherData.temp?.roundToInt().toString(), fontSize = 50.sp)
                            Text(text = "°C", fontSize = 30.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = weatherData.weather?.description!!, fontSize = 30.sp)

                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = "Precipitation: " + round((weatherData.precip ?: .0)).toString() + " mm/h", fontSize = 20.sp)
                Text(text = "Pressure: " + round((weatherData.pres ?: .0)).toString() + " mb", fontSize = 20.sp)
                Text(text = "Humidity: " + round(weatherData.rh ?: .0).toString() + " %", fontSize = 20.sp)
                Text(text = "Wind: " + round((weatherData.wind_spd ?: .0) * 3.6).toString() + " km/h", fontSize = 20.sp)
            }
        }
        else
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.padding(100.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    LoadingAnimation(darkMode = isDarkTheme)
                }
            }
        }
    }
}