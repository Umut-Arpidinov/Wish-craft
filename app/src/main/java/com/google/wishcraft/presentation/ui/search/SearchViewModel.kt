package com.google.wishcraft.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.wishcraft.common.base.BaseViewModel
import com.google.wishcraft.domain.models.UserListResponse
import com.google.wishcraft.domain.models.WishResponse
import com.google.wishcraft.domain.usecases.FollowUserUseCase
import com.google.wishcraft.domain.usecases.GetUserByNameUseCase
import com.google.wishcraft.domain.usecases.GetUserInfoUseCase
import com.google.wishcraft.domain.usecases.GetWishesUseCase
import com.google.wishcraft.domain.usecases.UnfollowUserUseCase
import com.google.wishcraft.presentation.utils.PEOPLE
import com.google.wishcraft.presentation.utils.WISH

class SearchViewModel(
    private val getUserByNameUseCase: GetUserByNameUseCase,
    private val getWishesUseCase: GetWishesUseCase,
    private val followUserUseCase: FollowUserUseCase,
    private val unfollowUserUseCase: UnfollowUserUseCase
    ) : BaseViewModel() {


    private var currentSearchType = PEOPLE // default to people


    private val _users: MutableLiveData<UserListResponse?> = MutableLiveData()
    val users: LiveData<UserListResponse?> get() = _users

    private val _wishes: MutableLiveData<WishResponse?> = MutableLiveData()
    val wishes: LiveData<WishResponse?> get() = _wishes


    init {
        searchUsersByName("")
    }

    fun searchCurrentQuery(query: String) {
        when (currentSearchType) {
            PEOPLE -> {
                _wishes.value = null
                searchUsersByName(query)
            }

            WISH -> {
                _users.value = null
                searchWishByName(query)
            }
        }
    }

    fun setCurrentSearchType(type: String) {
        currentSearchType = type
    }

    fun followUser(id: Int) {
        request(source = {followUserUseCase.invoke(id)}) {
            searchUsersByName("")
        }
    }

    fun unfollowUser(id: Int) {
        request(source = {unfollowUserUseCase.invoke(id)}) {
            searchUsersByName("")
        }
    }


    private fun searchUsersByName(name: String) {
        request(source = { getUserByNameUseCase.invoke(name) }) {
            _users.value = it

        }
    }

    private fun searchWishByName(name: String) {
        request(source = {
            getWishesUseCase.invoke(giftName = name)
        }) {
            _wishes.value = it
        }
    }

}
