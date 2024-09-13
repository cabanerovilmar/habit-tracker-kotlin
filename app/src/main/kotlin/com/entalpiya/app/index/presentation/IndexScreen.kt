package com.entalpiya.app.index.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.entalpiya.app.BuildConfig.WEB_BASE_URL
import com.entalpiya.app.core.utils.MarkdownKatexView

@Composable
fun IndexScreen(jsonContent: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            Text(
                text = "Entalpiya $WEB_BASE_URL",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
            )

            WebviewSample(jsonContent)
        }
    }
}

@Composable
fun WebviewSample(arg: String) {
    AndroidView(factory = {
        MarkdownKatexView(it).apply {
            text = arg
        }
    }, update = {
        it.text = arg
    })
}