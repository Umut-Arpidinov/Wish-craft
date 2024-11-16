package com.google.wishcraft.domain.interfaces

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.domain.models.MovieResponse
import com.google.wishcraft.domain.models.WishResponse

interface MainRepository {
    suspend fun getMovies(): ApiResult<MovieResponse>

    suspend fun getWishes(): ApiResult<WishResponse>
}