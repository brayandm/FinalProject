package com.example.finalproject.screens

import android.content.Context
import android.content.Intent
import android.text.Layout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.AppPreferences
import com.example.finalproject.LoginActivity
import com.example.finalproject.MainActivity
import com.example.finalproject.navigation.BottomNavigationScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SettingsScreen(context: MainActivity, isDarkTheme: MutableState<Boolean>,
                   appPreferences: AppPreferences) {
    Column {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Settings", fontSize = 35.sp)

            Spacer(modifier = Modifier.padding(10.dp))
        }

        Divider(thickness = 1.dp)

        Spacer(modifier = Modifier.padding(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Dark Mode", fontSize = 20.sp)

            Spacer(modifier = Modifier.padding(10.dp))

            Switch(
                checked = isDarkTheme.value,
                onCheckedChange = {
                    isDarkTheme.value = !isDarkTheme.value
                    appPreferences.setDarkMode(isDarkTheme.value)
                }
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Divider(thickness = 1.dp)

        Spacer(modifier = Modifier.padding(10.dp))

        Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Button(onClick = {context.logout(Firebase.auth)},
                modifier = Modifier.padding(30.dp))
                {
                    Text(text = "logout", fontSize = 20.sp)
                }
            }
        }

    }
}