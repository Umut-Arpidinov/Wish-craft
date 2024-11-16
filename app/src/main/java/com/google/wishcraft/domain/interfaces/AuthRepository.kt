package com.google.wishcraft.domain.interfaces

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.models.AuthTokenResponse
import com.google.wishcraft.domain.models.UserAuthModel

interface AuthRepository {

    fun saveToken(token: String)

    val isAuthenticated: Boolean

    suspend fun registerUser(
        userModel: UserAuthModel
    ): ApiResult<AuthTokenResponse>

    suspend fun login(
        userModel: UserAuthModel
    ): ApiResult<AuthTokenResponse>

    fun logOut()

}