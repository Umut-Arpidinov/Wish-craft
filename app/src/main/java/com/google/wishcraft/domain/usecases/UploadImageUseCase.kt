package com.google.wishcraft.domain.usecases

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.interfaces.MainRepository
import com.google.wishcraft.domain.models.ImageRequestBody
import com.google.wishcraft.domain.models.StaticObject
import com.google.wishcraft.domain.models.StaticObjectResponse

class UploadImageUseCase(
    private val repository: MainRepository
) {

    suspend operator fun invoke(body: ImageRequestBody): ApiResult<StaticObjectResponse> {
        return repository.uploadImage(body)
    }
}