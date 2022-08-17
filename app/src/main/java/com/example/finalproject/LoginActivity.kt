package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.finalproject.screens.LoginScreen
import com.example.finalproject.ui.theme.FinalProjectTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val auth = Firebase.auth

                    if (auth.currentUser != null) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }

                    LoginScreen(this, auth)
                }
            }
        }
    }

    fun register(auth: FirebaseAuth, username: MutableState<String>,
                         password: MutableState<String>)
    {
        auth.createUserWithEmailAndPassword(
            username.value,
            password.value
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(applicationContext, "Successful registration",
                    Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.exception?.message, Toast.LENGTH_LONG).show()
                Log.d("LoginActivity", "Authentication failed. Error: ${it.exception}")
            }
        }
    }

    fun login(auth: FirebaseAuth, username: MutableState<String>,
                      password: MutableState<String>)
    {
        auth.signInWithEmailAndPassword(
            username.value,
            password.value
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(applicationContext, "Successful login",
                    Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.exception?.message, Toast.LENGTH_LONG).show()
                Log.d("LoginActivity", "Authentication failed. Error: ${it.exception}")
            }
        }
    }
}
