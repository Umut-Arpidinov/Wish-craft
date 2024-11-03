package com.google.wishcraft.presentation.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.google.wishcraft.common.base.BaseActivity
import com.google.wishcraft.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(ActivityMainBinding::inflate) {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initialize() {
        super.initialize()
    }

    override fun observe() {
        super.observe()
    }
}

