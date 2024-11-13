package com.google.wishcraft.data.repository

import com.google.wishcraft.data.remote.ApiService
import com.google.wishcraft.domain.interfaces.AuthRepository

class AuthRepositoryImpl(
    private val apiService: ApiService
) : AuthRepository {

    override fun registerUser() {
    }
}