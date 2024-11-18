package com.example.coldsounds.main

import androidx.lifecycle.LiveData
import com.example.coldsounds.core.BaseViewModel
import com.example.coldsounds.core.RunAsync
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: Navigation.Mutable,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    fun navigationLiveData(): LiveData<Screen> = navigation.liveData()
}


