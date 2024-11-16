package com.google.wishcraft.data.local

interface AuthLocalSource {
    var accessToken: String?

    var refreshToken: String?

    fun logOut()

}