package com.google.wishcraft.presentation.ui.addWish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.wishcraft.common.base.BaseViewModel
import com.google.wishcraft.domain.models.ImageRequestBody
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.domain.usecases.CreateNewWishUseCase
import com.google.wishcraft.domain.usecases.UploadImageUseCase
import java.io.File

class AddWishViewModel(
    private val uploadImageUseCase: UploadImageUseCase,
    private val createNewWishUseCase: CreateNewWishUseCase
) : BaseViewModel(), ImageRequestBody.UploadCallback {


    var selectedImageId = -1

    private val _wishCreated: MutableLiveData<Unit> = MutableLiveData()

    val wishCreated: LiveData<Unit> = _wishCreated

    fun sendImageUri(file: File) {
        val body = ImageRequestBody(file, "image", this)
        request(source = { uploadImageUseCase.invoke(body) }) {
            it.icon?.id?.let { id ->
                selectedImageId = id
            }
        }
    }

    fun createNewWish(wish: Wish) {
        request(source = { createNewWishUseCase.invoke(wish) }) {
            _wishCreated.value = it
        }
    }

    override fun onProgressUpdate(percentage: Int) {
        println(percentage)
    }
}