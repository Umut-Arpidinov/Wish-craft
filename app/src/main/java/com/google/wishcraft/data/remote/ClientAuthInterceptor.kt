package com.google.wishcraft.data.remote

import com.google.wishcraft.common.uitls.AppConstants
import com.google.wishcraft.data.local.AuthLocalSource
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ClientAuthInterceptor(
    private val authLocalSource: AuthLocalSource
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        authLocalSource.accessToken?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}
