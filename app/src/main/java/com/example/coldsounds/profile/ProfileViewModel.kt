package com.example.coldsounds.profile

import androidx.lifecycle.ViewModel
import com.example.coldsounds.google_auth_ui_client.presentation.SignInScreen

import com.example.coldsounds.main.Navigation
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigation: Navigation.Navigate,
): ViewModel() {
    fun signOut(auth: FirebaseAuth) {
        auth.signOut()
        navigation.updateUi(SignInScreen)
    }


}