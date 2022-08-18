package com.example.finalproject.systemui

import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

class SystemUiStatusBar(private val window: Window) {

    fun setStatusBarColor(statusBarColor: Color, darkIcons: Boolean = true) {
        window.statusBarColor = statusBarColor.toArgb()
        if (Build.VERSION.SDK_INT >= 23) {
            if (darkIcons) {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }
}

class SystemUiNavigationNar(private val window: Window) {
    fun setNavigationBarColor(navigationBarColor: Color, darkIcons: Boolean = true) {
        window.navigationBarColor = navigationBarColor.toArgb()
        if (Build.VERSION.SDK_INT >= 26) {
            if (darkIcons) {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
        }
    }
}
