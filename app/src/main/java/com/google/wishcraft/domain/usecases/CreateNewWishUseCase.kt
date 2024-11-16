package com.google.wishcraft.domain.usecases

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.interfaces.MainRepository
import com.google.wishcraft.domain.models.Wish

class CreateNewWishUseCase(
    private val repository: MainRepository
) {

    suspend operator fun invoke(wish: Wish): ApiResult<Unit> {
        return repository.createNewWish(wish)
    }
}