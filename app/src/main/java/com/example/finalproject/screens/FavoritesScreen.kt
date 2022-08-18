package com.example.finalproject.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.location.LocationCity
import com.example.finalproject.model.CityFavorite
import com.example.finalproject.navigation.BottomNavigationScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(context: MainActivity, navController: NavHostController, isDarkTheme: MutableState<Boolean>) {
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

        Spacer(modifier = Modifier.padding(10.dp))

        val cityFavorites = context.viewModel.getCityFavoritesFromDatabase().observeAsState()

        Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {

            for(city in cityFavorites.value?: listOf())
            {
                Row()
                {

                    Surface(
                        shape = MaterialTheme.shapes.medium,
                        elevation = 1.dp,
                        // surfaceColor color will be changing gradually from primary to surface
                        color = if(isDarkTheme.value) darkColors().primary else lightColors().primary,
                        // animateContentSize will change the Surface size gradually
                        modifier = Modifier
                            .animateContentSize()
                            .padding(1.dp)
                            .width(350.dp)
                            .height(50.dp),
                    )
                    {
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            IconButton(onClick = {
                                context.viewModel.setLocationCity(LocationCity(city.cityName, city.countryName))
                                context.viewModel.setIsMyLocation(false)
                                context.viewModel.setIndexBottomNavigation(0)
                                context.viewModel.navigationStack.value!!.push(BottomNavigationScreens.Favorites)
                                navController.navigate(BottomNavigationScreens.Home.route)}) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_search),
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_delete),
                                    contentDescription = "",
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.padding(20.dp))

                            Text(text = "${city.cityName}, ${city.countryName}",
                                color = if(isDarkTheme.value) darkColors().onPrimary else lightColors().onPrimary,
                                fontSize = 20.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))
            }

            Scaffold(
                topBar = {},
                bottomBar = {},
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            context.viewModel.setIsMyLocation(true)
                            context.viewModel.setIndexBottomNavigation(0)
                            context.viewModel.navigationStack.value!!.push(BottomNavigationScreens.Favorites)
                            navController.navigate(BottomNavigationScreens.Home.route) },
                        content = {

                            Icon(painter = painterResource(R.drawable.ic_location),
                                contentDescription = "", modifier = Modifier.padding(20.dp)
                                    .size(30.dp))
                        },
                        backgroundColor = MaterialTheme.colors.primary
                    )
                },
                content = {})
        }
    }
}