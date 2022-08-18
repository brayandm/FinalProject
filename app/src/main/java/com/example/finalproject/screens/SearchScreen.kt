package com.example.finalproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen() {
    Column {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Search", fontSize = 35.sp)

            Spacer(modifier = Modifier.padding(10.dp))
        }

        Divider(thickness = 1.dp)
    }
}