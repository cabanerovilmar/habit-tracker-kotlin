package com.entalpiya.app.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.entalpiya.app.R
import com.entalpiya.app.core.presentation.ui.theme.EntalpiyaTheme
import com.entalpiya.app.index.presentation.IndexScreen
import com.entalpiya.app.core.utils.readJsonContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Entalpiya)
        setContent {
            EntalpiyaTheme {
                val jsonContent = readJsonContent(this@MainActivity)
                EntalpiyaApp(jsonContent)
            }
        }
    }

    @Composable
    private fun EntalpiyaApp(jsonContent: String) {
        IndexScreen(jsonContent)
    }
}
