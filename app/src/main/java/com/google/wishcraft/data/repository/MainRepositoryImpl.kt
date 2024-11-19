package com.google.wishcraft.data.repository

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.common.uitls.apiRequest
import com.google.wishcraft.data.remote.ApiService
import com.google.wishcraft.domain.interfaces.MainRepository
import com.google.wishcraft.domain.models.ImageRequestBody
import com.google.wishcraft.domain.models.MovieResponse
import com.google.wishcraft.domain.models.StaticObject
import com.google.wishcraft.domain.models.StaticObjectResponse
import com.google.wishcraft.domain.models.Wish
import com.google.wishcraft.domain.models.User
import com.google.wishcraft.domain.models.UserListResponse
import com.google.wishcraft.domain.models.UserResponse
import com.google.wishcraft.domain.models.WishResponse
import okhttp3.MultipartBody

class MainRepositoryImpl(
    private val apiService: ApiService
) : MainRepository {

    override suspend fun getMovies(): ApiResult<MovieResponse> {
        return apiRequest { apiService.getMovies() }
    }

    override suspend fun getWishes(
        userId: Int?,
        giftName: String?
    ): ApiResult<WishResponse> {
        return apiRequest { apiService.getWishes(
            userId = userId,
            giftName = giftName
        ) }
    }

    override suspend fun uploadImage(body: ImageRequestBody): ApiResult<StaticObjectResponse> {
        val part = MultipartBody.Part.createFormData("image", body.getFileName(), body)
        return apiRequest { apiService.uploadImage(part) }
    }

    override suspend fun createNewWish(wish: Wish): ApiResult<Unit> {
        return apiRequest { apiService.createNewWish(wish) }
    }

    override suspend fun getUserInfo(): ApiResult<UserResponse> {
        return apiRequest { apiService.getUserInfo() }
    }

    override suspend fun copyWish(id: Int): ApiResult<Unit> {
        return apiRequest { apiService.copyWish(id) }
    }

    override suspend fun getUserByName(userName: String): ApiResult<UserListResponse> {
        return apiRequest { apiService.getUserByName(userName) }
    }

    override suspend fun followUser(id: Int): ApiResult<Unit> {
        return apiRequest { apiService.followUser(id) }
    }

    override suspend fun unfollowUser(id: Int): ApiResult<Unit> {
        return apiRequest { apiService.unfollowUser(id) }
    }


}