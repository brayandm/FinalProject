package com.example.finalproject.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.location.LocationCity
import com.example.finalproject.navigation.BottomNavigationScreens

@Composable
fun SearchScreen(context: MainActivity, navController: NavHostController) {
    Column {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Search", fontSize = 35.sp)

            Spacer(modifier = Modifier.padding(10.dp))
        }

        Divider(thickness = 1.dp)

        Spacer(modifier = Modifier.padding(10.dp))

        AddUserInputAction(context, navController)
    }
}

@Composable
fun AddUserInputAction(context: MainActivity, navController: NavHostController) {

    val cityName = remember { mutableStateOf("") }
    val countryName = remember { mutableStateOf("") }

    Column (modifier = Modifier.verticalScroll(rememberScrollState())){

        Row (horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()){

            TextField(
                value = cityName.value,
                onValueChange = { cityName.value = it },
                label = { Text("City") },
                modifier = Modifier
                    .width(160.dp)
                    .height(60.dp)
            )

            Spacer(modifier = Modifier.padding(10.dp))

            TextField(
                value = countryName.value,
                onValueChange = { countryName.value = it },
                label = { Text("Country") },
                modifier = Modifier
                    .width(160.dp)
                    .height(60.dp)
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Column(horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxSize().fillMaxHeight()) {
            FloatingActionButton(
                onClick = {
                    context.viewModel.setLocationCity(
                        LocationCity(
                            cityName.value,
                            countryName.value
                        )
                    )
                    context.viewModel.setIsMyLocation(false)
                    context.viewModel.setIndexBottomNavigation(0)
                    context.viewModel.navigationStack.value!!.push(
                        BottomNavigationScreens.Favorites
                    )
                    navController.navigate(BottomNavigationScreens.Home.route) },
                content = {

                    Icon(painter = painterResource(R.drawable.ic_search),
                        contentDescription = "", modifier = Modifier
                            .padding(20.dp)
                            .size(30.dp))
                },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(20.dp)
            )
        }

    }
}