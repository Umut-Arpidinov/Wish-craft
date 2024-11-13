package com.google.wishcraft.presentation.ui.authentication.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentLoginBinding
import com.google.wishcraft.presentation.ui.authentication.AuthViewModel

class LoginFragment:
    BaseFragment<AuthViewModel, FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override val viewModel: AuthViewModel by viewModels()


    override fun initialize() {
        super.initialize()
    }

    override fun initClicks()= with(binding){
        super.initClicks()
        btnLogin.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToMainFragment()
            )
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }

    override fun onError(message: String) {
        super.onError(message)
    }

    override fun onLoading(loading: Boolean) {
        super.onLoading(loading)
    }

}