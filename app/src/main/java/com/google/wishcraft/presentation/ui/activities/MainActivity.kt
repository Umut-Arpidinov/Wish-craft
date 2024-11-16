package com.google.wishcraft.presentation.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.google.wishcraft.NavAuthDirections
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseActivity
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainAuthViewModel, ActivityMainBinding>
    (ActivityMainBinding::inflate), BaseFragment.AuthenticationListener {

    override val viewModel: MainAuthViewModel by viewModel<MainAuthViewModel>()

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHost
        navController = navHostFragment.navController
    }

    override fun initialize() {
        super.initialize()
    }

    override fun observe() {
        super.observe()
        viewModel.authState.observe(this) {
           it?.let {
               onAuthStateReceived(state = it)
           }
        }
    }

    private fun onAuthStateReceived(state: MainAuthViewModel.AuthState) {
        when(state) {
            MainAuthViewModel.AuthState.AUTHENTICATED -> {
                navController.navigate(
                    NavAuthDirections.toMainFragment()
                )
            }
            MainAuthViewModel.AuthState.UNAUTHENTICATED -> {
                navController.navigate(
                    NavAuthDirections.toAuth()
                )
            }
            MainAuthViewModel.AuthState.USER_CANCELLED -> {

            }
        }
    }

    override fun logout() {
        viewModel.logOut()
    }
}

