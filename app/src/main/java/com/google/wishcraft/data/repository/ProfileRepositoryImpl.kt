package com.google.wishcraft.data.repository

import com.google.wishcraft.data.remote.ApiService
import com.google.wishcraft.domain.interfaces.ProfileRepository

class ProfileRepositoryImpl(
    private val apiService: ApiService
): ProfileRepository {

    override fun getUserProfile() {

    }
}