package com.example.coldsounds.main

import com.example.coldsounds.core.LiveDataWrapper
import com.example.coldsounds.core.ProvideLiveData
import com.example.coldsounds.core.UiUpdate
import javax.inject.Inject
import javax.inject.Singleton

interface Navigation {

    interface Navigate : UiUpdate<Screen>

    interface Read : ProvideLiveData<Screen>

    interface Mutable : Navigate, Read

    @Singleton
    class Base @Inject constructor(): Mutable, LiveDataWrapper.Single<Screen>()
}