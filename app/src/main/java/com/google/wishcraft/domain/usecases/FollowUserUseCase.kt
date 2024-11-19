package com.google.wishcraft.domain.usecases

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.interfaces.MainRepository

class FollowUserUseCase (
    private val repository: MainRepository
) {
    suspend  operator fun invoke(id: Int): ApiResult<Unit> {
        return repository.followUser(id)
    }
}