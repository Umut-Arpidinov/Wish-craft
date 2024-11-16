package com.google.wishcraft.presentation.ui.events

import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentEventsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment:
    BaseFragment<EventsViewModel, FragmentEventsBinding>(FragmentEventsBinding::inflate) {

    override val viewModel: EventsViewModel by viewModel()

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