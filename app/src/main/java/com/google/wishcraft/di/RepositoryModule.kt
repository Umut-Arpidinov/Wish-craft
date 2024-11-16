package com.google.wishcraft.di

import com.google.wishcraft.data.repository.AuthRepositoryImpl
import com.google.wishcraft.data.repository.MainRepositoryImpl
import com.google.wishcraft.domain.interfaces.AuthRepository
import com.google.wishcraft.domain.interfaces.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(),get()) }
}