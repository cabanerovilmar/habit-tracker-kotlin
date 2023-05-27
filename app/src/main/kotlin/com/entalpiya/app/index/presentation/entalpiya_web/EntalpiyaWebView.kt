package com.entalpiya.app.index.presentation.entalpiya_web

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.entalpiya.app.BuildConfig
import com.entalpiya.app.common.presentation.MainViewModel

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun EntalpiyaWebView(mainViewModel: MainViewModel) {
    mainViewModel.setIsLoading(false)

    val url = BuildConfig.WEB_BASE_URL

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true

            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}