package com.entalpiya.app.core.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.entalpiya.app.core.presentation.ui.theme.EntalpiyaTheme
import com.entalpiya.app.core.utils.isNetworkAvailable
import com.entalpiya.app.core.utils.setStatusBarColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private var hasInternet: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hasInternet = isNetworkAvailable(this)
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                mainViewModel.isLoading.value
            }
        }
        setContent {
            EntalpiyaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Entalpiya",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(10.dp)
                        )

                    }
                    mainViewModel.setIsLoading(value = false)
                    val view = LocalView.current
                    val isDark = isSystemInDarkTheme()
                    SideEffect {
                        lifecycleScope.launch { setStatusBarColor(view, isDark) }
                    }
                }
            }
        }

    }
}
