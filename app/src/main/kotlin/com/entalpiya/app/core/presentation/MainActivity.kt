package com.entalpiya.app.core.presentation

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.entalpiya.app.core.presentation.ui.theme.EntalpiyaTheme
import kotlinx.coroutines.delay
import java.util.Date

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                mainViewModel.isLoading.value
            }
        }
        setContent {
            EntalpiyaTheme { // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android", mainViewModel = mainViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, mainViewModel: MainViewModel) {
    mainViewModel.setIsLoading(false)
    Text(text = "Hello $name!")
}