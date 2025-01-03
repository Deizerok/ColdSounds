package com.example.coldsounds.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel(
    private val runAsync: RunAsync
) : ViewModel() {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun <T : Any> runAsync(
        backgroundBlock: suspend () -> T,
        uiBlock: (T) -> Unit
    ) {
        runAsync.start(
            backgroundBlock,
            uiBlock,
            coroutineScope
        )
    }
}