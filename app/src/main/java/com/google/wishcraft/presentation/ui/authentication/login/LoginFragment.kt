package com.google.wishcraft.presentation.ui.authentication.login

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.wishcraft.NavAuthDirections
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentLoginBinding
import com.google.wishcraft.domain.models.UserAuthModel
import com.google.wishcraft.presentation.extensions.simpleDialog
import com.google.wishcraft.presentation.ui.authentication.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment:
    BaseFragment<AuthViewModel, FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override val viewModel: AuthViewModel by viewModel()


    override fun initialize() {
        super.initialize()
    }

    override fun initClicks()= with(binding){
        super.initClicks()
        btnLogin.setOnClickListener {
            registerUser()
        }
        llRegister.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
            )
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.authTokenResponse.observe(viewLifecycleOwner) {
            findNavController().navigate(
                NavAuthDirections.toMainFragment()
            )
        }
    }

    override fun onError(message: String) {
        super.onError(message)
        simpleDialog(message)
    }

    override fun onLoading(loading: Boolean) = with(binding) {
        super.onLoading(loading)
        progress.isVisible = loading
        btnLogin.isEnabled = !loading
    }

    private fun registerUser() = with(binding){
        val userEmail = etEmail.text.toString()
        val userPassword = etPassword.text.toString()

        val userModel = UserAuthModel(
            email = userEmail,
            password = userPassword
        )
        viewModel.login(userModel)
    }


}