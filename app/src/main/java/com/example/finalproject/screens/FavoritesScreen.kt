package com.example.finalproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.MainActivity
import com.example.finalproject.model.CityFavorite
import com.example.finalproject.navigation.BottomNavigationScreens

@Composable
fun FavoritesScreen(context: MainActivity) {
    Column {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.padding(10.dp),)

            Text(text = "Favorites", fontSize = 35.sp)

            Spacer(modifier = Modifier.padding(10.dp))
        }

        Divider(thickness = 1.dp)
        
        context.viewModel.addCityFavorite(CityFavorite(1,"Havana", "Cuba"))
        context.viewModel.addCityFavorite(CityFavorite(2,"Barcelona", "Spain"))

        val cityFavorites = context.viewModel.getCityFavoritesFromDatabase().observeAsState()

        Column() {
            for(city in cityFavorites.value?: listOf())
            {
                Text(text = "${city.cityName}, ${city.countryName}")
            }
        }
    }
}