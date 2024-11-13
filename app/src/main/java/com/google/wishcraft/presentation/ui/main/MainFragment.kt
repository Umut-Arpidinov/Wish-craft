package com.google.wishcraft.presentation.ui.main

import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentMainBinding
import com.google.wishcraft.presentation.extensions.gone
import com.google.wishcraft.presentation.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment :
    BaseFragment<MainViewModel, FragmentMainBinding>(FragmentMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModel()


    override fun initialize() {
        super.initialize()
        initNavigation()
    }

    override fun initClicks() = with(binding) {
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

    private fun initNavigation() {
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id !in SHOW_BOTTOM_NAV_VIEW_LIST) {
                hideNavigationBar()
            } else {
                showNavigationBar()
            }
        }
        binding.bottomNavView.setupWithNavController(navController)

    }

    private fun hideNavigationBar() {
        if (binding.bottomNavView.isVisible) {
            binding.bottomNavView.gone(true, 100)
        }
    }

    private fun showNavigationBar() {
        if (!binding.bottomNavView.isVisible) {
            binding.bottomNavView.visible(true)
        }
    }


    companion object {
        private val SHOW_BOTTOM_NAV_VIEW_LIST = setOf(
            R.id.homeFragment,
            R.id.searchFragment,
            R.id.addNewFragment,
            R.id.profileFragment,
        )
    }


}