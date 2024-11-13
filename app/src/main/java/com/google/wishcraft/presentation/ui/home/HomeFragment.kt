package com.google.wishcraft.presentation.ui.home

import androidx.core.view.isVisible
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentHomeBinding
import org.koin.androidx.navigation.koinNavGraphViewModel

class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by koinNavGraphViewModel(R.id.nav_home)

    override fun initialize() {
        super.initialize()

    }

    override fun initClicks()= with(binding){
        super.initClicks()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.movies.observe(viewLifecycleOwner) {
           binding.tvFilmCount.text = it.totalPages.toString()
        }
    }

    override fun onError(message: String) {
        super.onError(message)

    }

    override fun onLoading(loading: Boolean) {
        super.onLoading(loading)
        binding.progress.isVisible = loading
    }



}