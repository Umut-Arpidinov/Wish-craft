package com.google.wishcraft.di

import com.google.wishcraft.domain.usecases.GetMovieUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMovieUseCase(get()) }
}