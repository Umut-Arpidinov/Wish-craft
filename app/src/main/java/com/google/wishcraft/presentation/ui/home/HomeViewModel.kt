package com.google.wishcraft.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.wishcraft.common.base.BaseViewModel
import com.google.wishcraft.domain.models.MovieResponse
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.domain.models.WishResponse
import com.google.wishcraft.domain.usecases.GetMovieUseCase
import com.google.wishcraft.domain.usecases.GetWishesUseCase

class HomeViewModel(
    private val getWishesUseCase: GetWishesUseCase
) : BaseViewModel() {

    private val _wishes: MutableLiveData<WishResponse> = MutableLiveData()
    val wishes: LiveData<WishResponse> get() = _wishes


    init {
        getWishes()
    }
    fun getWishes() {
        request(source = {getWishesUseCase.invoke()}){
            _wishes.value = it
        }
    }

}