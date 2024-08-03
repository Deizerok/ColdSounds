package com.example.coldsounds.google_auth_ui_client.domain

import java.io.Serializable


data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?,
    val gmail: String?,
    ):Serializable