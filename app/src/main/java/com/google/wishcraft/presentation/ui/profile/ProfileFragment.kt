package com.google.wishcraft.presentation.ui.profile

import androidx.fragment.app.viewModels
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentProfileBinding
import com.google.wishcraft.presentation.ui.authentication.AuthViewModel

class ProfileFragment:
    BaseFragment<AuthViewModel, FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override val viewModel: AuthViewModel by viewModels()

    override fun initialize() {
        super.initialize()
    }

    override fun initClicks()= with(binding){
        super.initClicks()
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