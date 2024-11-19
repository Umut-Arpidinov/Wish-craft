package com.google.wishcraft.di

import com.google.wishcraft.presentation.ui.activities.MainAuthViewModel
import com.google.wishcraft.presentation.ui.addWish.AddWishViewModel
import com.google.wishcraft.presentation.ui.authentication.AuthViewModel
import com.google.wishcraft.presentation.ui.events.EventsViewModel
import com.google.wishcraft.presentation.ui.home.HomeViewModel
import com.google.wishcraft.presentation.ui.main.MainViewModel
import com.google.wishcraft.presentation.ui.profile.ProfileViewModel
import com.google.wishcraft.presentation.ui.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { MainAuthViewModel(get()) }
    viewModel { ProfileViewModel(get()) }

    viewModel { ProfileViewModel(get()) }
    viewModel { AddWishViewModel(get(), get()) }
    viewModel { EventsViewModel() }
    viewModel { SearchViewModel(get(), get(), get(), get()) }
}