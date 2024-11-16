package com.google.wishcraft.presentation.ui.search

import androidx.fragment.app.viewModels
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentSearchBinding

class SearchFragment :
    BaseFragment<SearchViewModel, FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel by viewModels()

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