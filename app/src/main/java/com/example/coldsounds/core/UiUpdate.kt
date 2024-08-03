package com.example.coldsounds.core

interface UiUpdate<T : Any> {

    fun updateUi(value: T)
}