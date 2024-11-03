package com.google.wishcraft.data.di

import android.app.Application
import com.chuckerteam.chucker.BuildConfig
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.wishcraft.common.uitls.AppConstants
import com.google.wishcraft.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideChucker(app: Application) =
    ChuckerInterceptor.Builder(app)
        .collector(ChuckerCollector(app))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(false)
        .build()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
): Retrofit = Retrofit.Builder()
    .addConverterFactory(gsonConverterFactory)
    .baseUrl(AppConstants.BASE_URL)
    .client(okHttpClient)
    .build()

fun provideInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }


fun provideGsonConverterFactory(
    gson: Gson
): GsonConverterFactory =
    GsonConverterFactory.create(gson)

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    chuckerInterceptor: ChuckerInterceptor,
) = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor(chuckerInterceptor)
    .retryOnConnectionFailure(true)
    .connectTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(2, TimeUnit.MINUTES)
    .readTimeout(2, TimeUnit.MINUTES)
    .build()


fun provideService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

val networkModule = module {
    single { provideOkHttpClient(get(),get()) }
    single { provideGson() }
    single { provideGsonConverterFactory(get()) }
    single { provideInterceptor() }
    single { provideChucker(get()) }
    single { provideRetrofit(get(),get()) }
    single { provideService(get()) }

}
