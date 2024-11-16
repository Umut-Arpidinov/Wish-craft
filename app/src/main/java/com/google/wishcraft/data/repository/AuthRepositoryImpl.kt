package com.google.wishcraft.data.repository

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.common.uitls.apiRequest
import com.google.wishcraft.data.local.AuthLocalSource
import com.google.wishcraft.data.remote.ApiService
import com.google.wishcraft.domain.interfaces.AuthRepository
import com.google.wishcraft.domain.models.AuthTokenResponse
import com.google.wishcraft.domain.models.UserAuthModel

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val localSource: AuthLocalSource
) : AuthRepository {

    override suspend fun registerUser(userModel: UserAuthModel): ApiResult<AuthTokenResponse> {
        return apiRequest { apiService.registerUser(userModel) }
    }

    override suspend fun login(userModel: UserAuthModel): ApiResult<AuthTokenResponse> {
        return apiRequest { apiService.login(userModel) }
    }

    override fun saveToken(token: String) {
        localSource.accessToken = token
    }

    override val isAuthenticated: Boolean
        get() = localSource.accessToken != null

    override fun logOut() {
        localSource.logOut()
    }
}