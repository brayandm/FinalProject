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
import com.example.finalproject.systemui.SystemUiNavigationNar
import com.example.finalproject.systemui.SystemUiStatusBar
import com.example.finalproject.ui.theme.FinalProjectTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        viewModel.setIsWeatherLoad(false)

        setContent {

            val isDarkTheme = remember { mutableStateOf(appPreferences.getDarkMode()) }
            val isFahrenheit = remember { mutableStateOf(appPreferences.getFahrenheit()) }

            FinalProjectTheme {

                MaterialTheme(colors = if(isDarkTheme.value) darkColors() else lightColors())
                {
                    SystemUiStatusBar(window = window).setStatusBarColor(MaterialTheme.colors.primary, isDarkTheme.value)
                    SystemUiNavigationNar(window = window).setNavigationBarColor(MaterialTheme.colors.primary, isDarkTheme.value)

                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        val navController = rememberNavController()

                        Scaffold(
                            bottomBar = {
                                AddBottomBarNavigation(context = this@MainActivity,
                                    navController = navController)
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
                                    isFahrenheit = isFahrenheit,
                                    appPreferences = appPreferences)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!viewModel.navigationStack.value!!.empty()) {
            val top = viewModel.navigationStack.value!!.pop()
            when(top.route) {
                "Home" -> viewModel.setIndexBottomNavigation(0)
                "Favorites" -> viewModel.setIndexBottomNavigation(1)
                "Settings" -> viewModel.setIndexBottomNavigation(2)
            }
        }
        super.onBackPressed()
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



