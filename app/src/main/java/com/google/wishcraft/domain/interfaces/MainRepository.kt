package com.google.wishcraft.domain.interfaces

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.models.ImageRequestBody
import com.google.wishcraft.domain.models.MovieResponse
import com.google.wishcraft.domain.models.User
import com.google.wishcraft.domain.models.UserResponse
import com.google.wishcraft.domain.models.StaticObject
import com.google.wishcraft.domain.models.StaticObjectResponse
import com.google.wishcraft.domain.models.UserListResponse
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.domain.models.WishResponse

interface MainRepository {
    suspend fun getMovies(): ApiResult<MovieResponse>

    suspend fun getWishes(
        userId: Int? = null,
        giftName: String? = null
    ): ApiResult<WishResponse>

    suspend fun getUserInfo(): ApiResult<UserResponse>

    suspend fun uploadImage(
        body: ImageRequestBody
    ): ApiResult<StaticObjectResponse>

    suspend fun createNewWish(
        wish: Wish
    ): ApiResult<Unit>

    suspend fun copyWish(
        id: Int
    ): ApiResult<Unit>

    suspend fun getUserByName(
        userName: String
    ): ApiResult<UserListResponse>

    suspend fun followUser(
        id: Int
    ): ApiResult<Unit>

    suspend fun unfollowUser(
        id: Int
    ): ApiResult<Unit>
}