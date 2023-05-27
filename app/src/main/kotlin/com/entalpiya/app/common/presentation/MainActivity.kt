package com.entalpiya.app.common.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.entalpiya.app.BuildConfig
import com.entalpiya.app.common.presentation.ui.theme.EntalpiyaTheme
import com.entalpiya.app.common.utils.isNetworkAvailable
import com.entalpiya.app.index.presentation.entalpiya_web.EntalpiyaWebView
import com.entalpiya.app.index.presentation.not_available_offline.NotAvailableOfflineView

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
//    private val hasInternet = isNetworkAvailable(this)
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
            EntalpiyaTheme { // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (hasInternet as Boolean) EntalpiyaWebView(mainViewModel) else NotAvailableOfflineView(
                        mainViewModel
                    )
                }
            }
        }
    }
}
