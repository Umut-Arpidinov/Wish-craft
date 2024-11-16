package com.google.wishcraft.domain.usecases

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.interfaces.MainRepository
import com.google.wishcraft.domain.models.User
import com.google.wishcraft.domain.models.UserResponse
import com.google.wishcraft.domain.models.WishResponse

class GetUserInfoUseCase (
    private val repository: MainRepository
) {
    suspend  operator fun invoke(): ApiResult<UserResponse> {
        return repository.getUserInfo()
    }
}