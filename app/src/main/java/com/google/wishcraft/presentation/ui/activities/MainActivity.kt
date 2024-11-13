package com.google.wishcraft.presentation.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseActivity
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>
    (ActivityMainBinding::inflate), BaseFragment.AuthenticationListener {

    override val viewModel: MainActivityViewModel by viewModels()

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
    }

    override fun logout() {

    }
}

