package com.google.wishcraft.presentation.ui.home

import androidx.core.view.isVisible
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentHomeBinding
import com.google.wishcraft.presentation.extensions.simpleDialog
import org.koin.androidx.navigation.koinNavGraphViewModel

class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by koinNavGraphViewModel(R.id.nav_home)

    private val wishListAdapter = WishListAdapter()

    override fun initialize() {
        super.initialize()
        binding.rvWishList.adapter = wishListAdapter
    }

    override fun initClicks()= with(binding){
        super.initClicks()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.wishes.observe(viewLifecycleOwner) {
            wishListAdapter.submitList(it.wishes)
        }
    }

    override fun onError(message: String) {
        super.onError(message)
        simpleDialog(message)
    }

    override fun onLoading(loading: Boolean) {
        super.onLoading(loading)
        binding.progress.isVisible = loading
    }

}