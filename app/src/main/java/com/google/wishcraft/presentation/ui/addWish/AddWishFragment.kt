package com.google.wishcraft.presentation.ui.addWish

import androidx.fragment.app.viewModels
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentAddWishBinding
import com.google.wishcraft.presentation.ui.authentication.AuthViewModel

class AddWishFragment:
    BaseFragment<AddWishViewModel, FragmentAddWishBinding>(FragmentAddWishBinding::inflate) {

    override val viewModel: AddWishViewModel by viewModels()


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