package com.google.wishcraft.data.repository

import com.google.wishcraft.common.uitls.ApiResult
import com.google.wishcraft.common.uitls.apiRequest
import com.google.wishcraft.data.remote.ApiService
import com.google.wishcraft.domain.interfaces.MainRepository
import com.google.wishcraft.domain.models.MovieResponse

class MainRepositoryImpl(
    private val apiService: ApiService
) : MainRepository {

    override suspend fun getMovies(): ApiResult<MovieResponse> {
        return apiRequest { apiService.getMovies() }
    }
}