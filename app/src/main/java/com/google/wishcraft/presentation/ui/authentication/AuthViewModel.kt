package com.google.wishcraft.presentation.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.wishcraft.common.base.BaseViewModel
import com.google.wishcraft.domain.interfaces.AuthRepository
import com.google.wishcraft.domain.models.UserAuthModel
import com.google.wishcraft.domain.usecases.LoginUseCase
import com.google.wishcraft.domain.usecases.RegisterUserUseCase

class AuthViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUseCase,
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _authTokenResponse: MutableLiveData<Unit> = MutableLiveData()
    val authTokenResponse: LiveData<Unit> get() = _authTokenResponse


    fun registerUser(userModel: UserAuthModel) {
        request(
            source = { registerUserUseCase.invoke(userModel) },
        ) {
            it.access?.token?.let { token ->
                authRepository.saveToken(token)
            }
            _authTokenResponse.value = Unit
        }
    }
    fun login(userModel: UserAuthModel) {
        request(
            source = { loginUseCase.invoke(userModel) },
        ) {
            it.access?.token?.let { token ->
                authRepository.saveToken(token)
            }
            _authTokenResponse.value = Unit
        }
    }
}