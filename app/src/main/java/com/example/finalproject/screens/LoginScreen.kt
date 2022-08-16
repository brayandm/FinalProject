package com.example.finalproject.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.LoginActivity
import com.example.finalproject.navigation.BottomNavigationScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(context: LoginActivity, auth: FirebaseAuth) {

    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            modifier = Modifier
                .width(300.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            modifier = Modifier
                .width(300.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Row {
            Button(onClick = { context.register(auth, username, password) }) {
                Text("Register")
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = { context.login(auth, username, password) }) {
                Text("Login")
            }
        }
    }
}