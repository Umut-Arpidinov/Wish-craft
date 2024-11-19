package com.google.wishcraft.domain.usecases

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.interfaces.MainRepository
import com.google.wishcraft.domain.models.UserListResponse

class GetUserByNameUseCase (
    private val repository: MainRepository
) {
    suspend  operator fun invoke(userName: String): ApiResult<UserListResponse> {
        return repository.getUserByName(userName)
    }
}