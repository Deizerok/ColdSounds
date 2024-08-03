package com.example.coldsounds.google_auth_ui_client.presentation

import android.content.Intent
import com.example.coldsound.google_auth_ui_client.presentation.SignInState
import com.example.coldsounds.profile.ProfileScreen
import com.example.coldsounds.core.BaseViewModel
import com.example.coldsounds.core.RunAsync
import com.example.coldsounds.google_auth_ui_client.domain.GoogleAuthUiClient
import com.example.coldsounds.google_auth_ui_client.domain.SignInResult
import com.example.coldsounds.google_auth_ui_client.domain.UserData
import com.example.coldsounds.main.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val navigation: Navigation.Navigate,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    private val _state = MutableStateFlow(SignInState())

    private fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    private fun goToProfileScreen(userData: UserData) {
        navigation.updateUi(ProfileScreen(userData))
    }

    fun checkSignIn(signedInUser: GoogleAuthUiClient) {
        runAsync({
            signedInUser.getSignedInUser()!!
        }, {
                goToProfileScreen(
                    UserData(
                        userId = it.userId,
                        gmail = it.gmail,
                        username = it.username,
                        profilePictureUrl = it.profilePictureUrl
                    )
                )
    })
}

    fun signIn(googleAuthUiClient: GoogleAuthUiClient, data: Intent) {
        var userData: UserData =  UserData(
            userId = "",
            username = "",
            profilePictureUrl = "" ,
            gmail = ""
        )
        var signInResult: SignInResult

        runAsync({
            signInResult = googleAuthUiClient.signInWithIntent(data)
            userData = googleAuthUiClient.getSignedInUser()!!
            onSignInResult(signInResult)
        },{
            goToProfileScreen(userData)
        })
    }
}