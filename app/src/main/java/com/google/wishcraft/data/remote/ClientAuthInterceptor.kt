package com.google.wishcraft.data.remote

import com.google.wishcraft.common.uitls.AppConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ClientAuthInterceptor(

) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        /*authLocalSource.accessToken?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }*/
        requestBuilder.addHeader(
            "Authorization", "Bearer ${AppConstants.API_KEY}"
        )
        return chain.proceed(requestBuilder.build())
    }
}
