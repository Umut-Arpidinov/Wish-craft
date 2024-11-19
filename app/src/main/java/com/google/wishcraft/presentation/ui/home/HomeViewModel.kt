package com.google.wishcraft.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.wishcraft.common.base.BaseViewModel
import com.google.wishcraft.common.uitls.SingleLiveEvent
import com.google.wishcraft.domain.models.MovieResponse
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.domain.models.WishResponse
import com.google.wishcraft.domain.usecases.CopyWishUseCase
import com.google.wishcraft.domain.usecases.GetMovieUseCase
import com.google.wishcraft.domain.usecases.GetWishesUseCase

class HomeViewModel(
    private val getWishesUseCase: GetWishesUseCase,
    private val copyWishUseCase: CopyWishUseCase
) : BaseViewModel() {

    private val _wishes: MutableLiveData<WishResponse> = MutableLiveData()
    val wishes: LiveData<WishResponse> get() = _wishes


    private val _wishCopyState: SingleLiveEvent<Unit> = SingleLiveEvent()
    val wishCopyState: LiveData<Unit> get() = _wishCopyState

    init {
        getWishes()
    }

    fun getWishes() {
        request(source = { getWishesUseCase.invoke() }) {
            _wishes.value = it
        }
    }

    fun refreshWish(refreshCallback: () -> Unit) {
        requestForRefresh(
            source = { getWishesUseCase.invoke() },
            onSuccess = {
                _wishes.value = it
            },
            refreshCallBack = {
                refreshCallback.invoke()
            }
        )
    }

    fun copyWish(id: Int) {
        request(source = { copyWishUseCase.invoke(id) }) {
            _wishCopyState.value = it
        }
    }

}