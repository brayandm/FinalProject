package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.api.WeatherApiClient
import com.example.finalproject.api.WeatherApiProvider
import com.example.finalproject.location.fetchLocation
import com.example.finalproject.navigation.AddBottomBarNavigation
import com.example.finalproject.navigation.AddNavigationContent
import com.example.finalproject.ui.theme.FinalProjectTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appPreferences = AppPreferences(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        viewModel.setIsWeatherLoad(false)

        setContent {

            val isDarkTheme = remember { mutableStateOf(appPreferences.getDarkMode()) }

            FinalProjectTheme {

                MaterialTheme(colors = if(isDarkTheme.value) darkColors() else lightColors())
                {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val navController = rememberNavController()

                        Scaffold(
                            bottomBar = {
                                AddBottomBarNavigation(navController = navController)
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = it.calculateBottomPadding())
                            ) {
                                AddNavigationContent(context = this@MainActivity,
                                    navController = navController,
                                    isDarkTheme = isDarkTheme,
                                    appPreferences = appPreferences)
                            }
                        }
                    }
                }
            }
        }
    }

    fun logout(auth: FirebaseAuth)
    {
        auth.signOut()
        Toast.makeText(applicationContext, "Successful logout",
            Toast.LENGTH_LONG).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}



