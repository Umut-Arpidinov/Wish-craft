package com.google.wishcraft.presentation.ui.authentication.registration

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.wishcraft.NavAuthDirections
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentRegisterBinding
import com.google.wishcraft.domain.models.UserAuthModel
import com.google.wishcraft.presentation.extensions.simpleDialog
import com.google.wishcraft.presentation.ui.authentication.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment:
    BaseFragment<AuthViewModel, FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override val viewModel: AuthViewModel by viewModel()


    override fun initialize() {
        super.initialize()
    }

    override fun initClicks()= with(binding){
        super.initClicks()
        btnRegister.setOnClickListener {
            registerUser()
        }

        llLogin.setOnClickListener {
            findNavController().navigateUp()
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

    override fun onLoading(loading: Boolean) = with(binding){
        super.onLoading(loading)
        progress.isVisible = loading
        btnRegister.isEnabled = !loading
    }
    private fun registerUser() = with(binding){
        val userName = etUserName.text.toString()
        val userEmail = etEmail.text.toString()
        val userPassword = etPassword.text.toString()

        val userModel = UserAuthModel(
            username = userName,
            email = userEmail,
            password = userPassword
        )
        viewModel.registerUser(userModel)
    }

}