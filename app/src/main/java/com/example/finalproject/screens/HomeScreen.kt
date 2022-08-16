package com.example.finalproject.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.MainActivity
import com.example.finalproject.MainViewModel
import com.example.finalproject.location.Location
import com.example.finalproject.navigation.BottomNavigationScreens

@Composable
fun HomeScreen(context: MainActivity) {

    val location = context.viewModel.location.observeAsState(Location(.0,.0))

    Column() {
        Text(text = "Home Screen")
        Text(location.value.latitude.toString())
        Text(location.value.longitude.toString())
    }
}