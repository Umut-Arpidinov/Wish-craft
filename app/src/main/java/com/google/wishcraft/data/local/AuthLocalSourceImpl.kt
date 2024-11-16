package com.google.wishcraft.data.local

import android.content.SharedPreferences

class AuthLocalSourceImpl(
    private val preferences: SharedPreferences
): AuthLocalSource {

    override var accessToken: String?
        set(token) = preferences.edit().putString(ACCESS_TOKEN, token).apply()
        get() = preferences.getString(ACCESS_TOKEN, null)

    override var refreshToken: String?
        set(token) = preferences.edit().putString(REFRESH_TOKEN, token).apply()
        get() = preferences.getString(REFRESH_TOKEN, null)

    override fun logOut() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val ACCESS_TOKEN = "com.google.wishcraft.access_token"
        private const val REFRESH_TOKEN = "com.google.wishcraft.refresh_token"
    }
}

