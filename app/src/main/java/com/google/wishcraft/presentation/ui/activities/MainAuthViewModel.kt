package com.google.wishcraft.presentation.ui.activities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.wishcraft.common.base.BaseViewModel
import com.google.wishcraft.domain.interfaces.AuthRepository

class MainAuthViewModel(
   private val authRepository: AuthRepository
) : BaseViewModel() {


    enum class AuthState {
        UNAUTHENTICATED,
        AUTHENTICATED,
        USER_CANCELLED
    }

    private val _authState: MutableLiveData<AuthState> = MutableLiveData()
    val authState: LiveData<AuthState> get() = _authState

    init {
        if (authRepository.isAuthenticated) _authState.value = AuthState.AUTHENTICATED
    }

    fun logOut() {
        authRepository.logOut()
        _authState.value = AuthState.UNAUTHENTICATED
    }

}