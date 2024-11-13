package com.google.wishcraft.presentation

import android.app.Application
import com.google.wishcraft.di.appModule
import com.google.wishcraft.di.networkModule
import com.google.wishcraft.di.repositoryModule
import com.google.wishcraft.di.useCaseModule
import com.google.wishcraft.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class WishCraftApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WishCraftApplication)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
        Timber.plant(Timber.DebugTree())
    }
}

