package com.google.wishcraft.common.di

import android.content.res.Resources
import com.google.wishcraft.common.uitls.ErrorConverter
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val appModule = module {
    single<Resources> { androidApplication().resources }
    single { ErrorConverter(get()) }
    single { androidApplication() }
}