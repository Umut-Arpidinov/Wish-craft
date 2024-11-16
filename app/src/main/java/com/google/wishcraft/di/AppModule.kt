package com.google.wishcraft.di

import android.content.Context
import android.content.res.Resources
import com.google.wishcraft.common.uitls.ErrorConverter
import com.google.wishcraft.data.local.AuthLocalSource
import com.google.wishcraft.data.local.AuthLocalSourceImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


private const val SHARED_PREFERENCES_NAME = "com.google.wishtCraft.preferences"


val appModule = module {
    single { androidContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE) }
    single<AuthLocalSource> { AuthLocalSourceImpl(get()) }
    single<Resources> { androidApplication().resources }
    single { ErrorConverter(get()) }
}