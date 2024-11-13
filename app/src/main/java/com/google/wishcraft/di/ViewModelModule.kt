package com.google.wishcraft.di

import com.google.wishcraft.presentation.ui.home.HomeViewModel
import com.google.wishcraft.presentation.ui.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}