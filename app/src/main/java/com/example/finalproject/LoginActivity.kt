package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.finalproject.screens.LoginScreen
import com.example.finalproject.systemui.SystemUiNavigationNar
import com.example.finalproject.systemui.SystemUiStatusBar
import com.example.finalproject.ui.theme.FinalProjectTheme
import com.example.finalproject.utils.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme = remember { mutableStateOf(appPreferences.getDarkMode()) }

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
    }

    fun register(auth: FirebaseAuth, username: MutableState<String>,
                         password: MutableState<String>)
    {
        if(username.value == "")
        {
            Toast.makeText(applicationContext, "Username field is empty",
                Toast.LENGTH_LONG).show()
            return
        }

        if(password.value == "")
        {
            Toast.makeText(applicationContext, "Password field is empty",
                Toast.LENGTH_LONG).show()
            return
        }

        if(!Utils().isGoodPassword(password.value))
        {
            if(password.value.length < 8)
            {
                Toast.makeText(applicationContext, "Insecure password, it should have at least 8 " +
                        "characters",
                    Toast.LENGTH_LONG).show()
                return
            }
            if(!Utils().containsLetter(password.value))
            {
                Toast.makeText(applicationContext, "Insecure password, it should have at least a letter",
                    Toast.LENGTH_LONG).show()
                return
            }
            if(!Utils().containsDigit(password.value))
            {
                Toast.makeText(applicationContext, "Insecure password, it should have at least a digit",
                    Toast.LENGTH_LONG).show()
                return
            }
        }

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
        if(username.value == "")
        {
            Toast.makeText(applicationContext, "Username field is empty",
                Toast.LENGTH_LONG).show()
            return
        }

        if(password.value == "")
        {
            Toast.makeText(applicationContext, "Password field is empty",
                Toast.LENGTH_LONG).show()
            return
        }

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
