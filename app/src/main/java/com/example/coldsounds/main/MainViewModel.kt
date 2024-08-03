package com.example.coldsounds.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.example.coldsounds.core.BaseViewModel
import com.example.coldsounds.core.RunAsync
import com.example.coldsounds.google_auth_ui_client.presentation.SignInScreen

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigation: Navigation.Mutable,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    fun navigationLiveData(): LiveData<Screen> = navigation.liveData()

    fun signInScreen(savedInstanceState: Bundle?) {
            navigation.updateUi(SignInScreen)
    }

//    fun init(firstRun: Boolean) {
//        if (firstRun) {
//            navigation.updateUi(SplashScreen)
//        }
//    }

//    fun startHomeScreen() = runAsync({
//        delay(4000)
//    }, {
//        navigation.updateUi(TaskBoardScreen)
//    })
}


