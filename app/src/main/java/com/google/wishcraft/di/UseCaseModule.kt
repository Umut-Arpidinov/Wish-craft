package com.google.wishcraft.di

import com.google.wishcraft.domain.usecases.CreateNewWishUseCase
import com.google.wishcraft.domain.usecases.GetMovieUseCase
import com.google.wishcraft.domain.usecases.GetUserInfoUseCase
import com.google.wishcraft.domain.usecases.GetWishesUseCase
import com.google.wishcraft.domain.usecases.LoginUseCase
import com.google.wishcraft.domain.usecases.RegisterUserUseCase
import com.google.wishcraft.domain.usecases.UploadImageUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMovieUseCase(get()) }
    factory { RegisterUserUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { GetWishesUseCase(get()) }
    factory { GetUserInfoUseCase(get()) }
    factory { UploadImageUseCase(get()) }
    factory { CreateNewWishUseCase(get()) }
}