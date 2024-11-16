package com.google.wishcraft.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.wishcraft.common.base.BaseViewModel
import com.google.wishcraft.domain.models.MovieResponse
import com.google.wishcraft.domain.models.User
import com.google.wishcraft.domain.models.UserResponse
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.domain.models.WishResponse
import com.google.wishcraft.domain.usecases.GetMovieUseCase
import com.google.wishcraft.domain.usecases.GetUserInfoUseCase
import com.google.wishcraft.domain.usecases.GetWishesUseCase

class ProfileViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : BaseViewModel() {

    private val _user: MutableLiveData<UserResponse> = MutableLiveData()
    val user: LiveData<UserResponse> get() = _user


    init {
        getUserInfo()
    }
    fun getUserInfo() {
        request(source = {getUserInfoUseCase.invoke()}){
            _user.value = it
        }
    }

}