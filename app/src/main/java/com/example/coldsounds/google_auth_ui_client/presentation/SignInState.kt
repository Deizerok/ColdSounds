package com.example.coldsound.google_auth_ui_client.presentation

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
