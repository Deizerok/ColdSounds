package com.example.coldsounds.google_auth_ui_client.domain

import com.example.coldsounds.google_auth_ui_client.domain.UserData

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

