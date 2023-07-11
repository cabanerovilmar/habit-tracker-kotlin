package com.entalpiya.app.core.utils

import android.app.Activity
import android.view.View
import androidx.core.view.WindowCompat
import kotlinx.coroutines.delay

suspend fun setStatusBarColor(view: View, isDark: Boolean) {
    val window = (view.context as Activity).window
    delay(375)
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
    WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !isDark
}
