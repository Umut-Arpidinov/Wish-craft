package com.google.wishcraft.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.wishcraft.common.base.BaseViewModel
import com.google.wishcraft.domain.models.MovieResponse
import com.google.wishcraft.domain.usecases.GetMovieUseCase

class HomeViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : BaseViewModel() {


    init {
        getMovies()
    }

    private val _movies = MutableLiveData<MovieResponse>()
    val movies: LiveData<MovieResponse> = _movies


    fun getMovies() {
        request(
            source = { getMovieUseCase.invoke()},
            onSuccess =  {
                _movies.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

}