package com.google.wishcraft.domain.usecases

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.interfaces.AuthRepository
import com.google.wishcraft.domain.models.AuthTokenResponse
import com.google.wishcraft.domain.models.UserAuthModel

class RegisterUserUseCase (
    private val repository: AuthRepository
) {
    suspend  operator fun invoke(userModel: UserAuthModel): ApiResult<AuthTokenResponse> {
        return repository.registerUser(userModel)
    }
}