package com.entalpiya.app.index.presentation.not_available_offline

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.entalpiya.app.common.presentation.MainViewModel

@Composable
fun NotAvailableOfflineView(mainViewModel: MainViewModel) {
    mainViewModel.setIsLoading(false)
    Text(text = "We apologize! Our app is not yet available for offline use.")
}