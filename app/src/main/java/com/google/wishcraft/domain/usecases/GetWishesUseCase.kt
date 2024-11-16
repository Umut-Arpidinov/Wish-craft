package com.google.wishcraft.domain.usecases

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.interfaces.MainRepository
import com.google.wishcraft.domain.models.WishResponse

class GetWishesUseCase (
    private val repository: MainRepository
) {
    suspend  operator fun invoke(): ApiResult<WishResponse> {
        return repository.getWishes()
    }
}