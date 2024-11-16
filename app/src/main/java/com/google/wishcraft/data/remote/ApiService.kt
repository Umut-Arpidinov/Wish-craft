package com.google.wishcraft.data.remote

import com.google.wishcraft.domain.models.AuthTokenResponse
import com.google.wishcraft.domain.models.MovieResponse
import com.google.wishcraft.domain.models.UserAuthModel
import com.google.wishcraft.domain.models.WishResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular?language=en-US")
    suspend fun getMovies(
        @Query("page") page: Int = 1,
    ): MovieResponse

    @POST("auth/register")
    suspend  fun registerUser(
       @Body userModel: UserAuthModel
    ): AuthTokenResponse

    @POST("auth/login")
    suspend fun login(
        @Body userModel: UserAuthModel
    ): AuthTokenResponse

    @GET("wishes")
    suspend fun getWishes(): WishResponse



}