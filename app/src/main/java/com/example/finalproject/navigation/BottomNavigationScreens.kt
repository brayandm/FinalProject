package com.example.finalproject.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalproject.AppPreferences
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.screens.FavoritesScreen
import com.example.finalproject.screens.HomeScreen
import com.example.finalproject.screens.SearchScreen
import com.example.finalproject.screens.SettingsScreen

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val stringResId: Int,
    @DrawableRes val drawResId: Int
) {
    object Home : BottomNavigationScreens("Home", R.string.main_navigation_home, R.drawable.ic_home)
    object Search : BottomNavigationScreens("Search", R.string.main_navigation_search, R.drawable.ic_search)
    object Favorites : BottomNavigationScreens("Favorites", R.string.main_navigation_favorites, R.drawable.ic_favourite)
    object Settings : BottomNavigationScreens("Settings", R.string.main_navigation_settings, R.drawable.ic_settings)
}

@Composable
fun AddBottomBarNavigation(context: MainActivity, navController: NavHostController) {

    val items = listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Search,
        BottomNavigationScreens.Favorites,
        BottomNavigationScreens.Settings
    )

    val selectedIndex = context.viewModel.indexBottomNavigation.observeAsState(0)

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
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
                alwaysShowLabel = true,
                onClick = {
                    if (!isSelected) {
                        context.viewModel.setIndexBottomNavigation(index)
                        context.viewModel.navigationStack.value?.push(items[context.viewModel.indexBottomNavigation.value!!])

                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
fun AddNavigationContent(context: MainActivity, navController: NavHostController,
                         isDarkTheme: State<Boolean>, isFahrenheit: State<Boolean>,
                         appPreferences: AppPreferences) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen(context, navController,
                isDarkTheme as MutableState<Boolean>,
                isFahrenheit as MutableState<Boolean>)
        }

        composable(BottomNavigationScreens.Search.route) {
            SearchScreen(context, navController)
        }

        composable(BottomNavigationScreens.Favorites.route) {
            FavoritesScreen(context, navController, isDarkTheme as MutableState<Boolean>)
        }

        composable(BottomNavigationScreens.Settings.route) {
            SettingsScreen(context, isDarkTheme as MutableState<Boolean>,
                isFahrenheit as MutableState<Boolean>, appPreferences)
        }
    }
}