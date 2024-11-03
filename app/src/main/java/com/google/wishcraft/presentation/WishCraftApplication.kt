package com.google.wishcraft.presentation

import android.app.Application
import com.google.wishcraft.common.di.appModule
import com.google.wishcraft.data.di.networkModule
import org.koin.core.context.startKoin
import timber.log.Timber

class WishCraftApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            modules(
                appModule,
                networkModule
            )
        }
    }
}

