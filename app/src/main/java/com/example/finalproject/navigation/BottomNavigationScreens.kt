package com.example.finalproject.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.R
import com.example.finalproject.screens.FavoritesScreen
import com.example.finalproject.screens.HomeScreen
import com.example.finalproject.screens.SettingsScreen

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val stringResId: Int,
    @DrawableRes val drawResId: Int
) {
    object Home : BottomNavigationScreens("Home", R.string.main_navigation_home, R.drawable.ic_home)
    object Favorites : BottomNavigationScreens("Favorites", R.string.main_navigation_favorites, R.drawable.ic_favourite)
    object Settings : BottomNavigationScreens("Settings", R.string.main_navigation_settings, R.drawable.ic_settings)
}

@Composable
fun AddBottomBarNavigation(navController: NavHostController) {

    val items = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Favorites,
        BottomNavigationScreens.Settings
    )

    val selectedIndex = remember { mutableStateOf(0) }

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.onSurface,
    ) {

        items.forEachIndexed { index, screen ->

            val isSelected = selectedIndex.value == index

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.drawResId),
                        contentDescription = stringResource(id = screen.stringResId),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        stringResource(id = screen.stringResId)
                    )
                },
                selected = isSelected,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.DarkGray,
                alwaysShowLabel = true,
                onClick = {
                    if (!isSelected) {
                        selectedIndex.value = index
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun AddNavigationContent(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen()
        }

        composable(BottomNavigationScreens.Favorites.route) {
            FavoritesScreen()
        }

        composable(BottomNavigationScreens.Settings.route) {
            SettingsScreen()
        }
    }
}