package com.example.coldsounds.profile

import androidx.fragment.app.Fragment
import com.example.coldsounds.google_auth_ui_client.domain.UserData
import com.example.coldsounds.main.Screen

data class ProfileScreen(
    private val userData: UserData,
) : Screen.Replace(ProfileFragment::class.java) {

    override fun fragment(): Fragment {
        return ProfileFragment.newInstance(userData)
    }
}