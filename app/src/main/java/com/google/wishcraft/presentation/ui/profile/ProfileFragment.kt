package com.google.wishcraft.presentation.ui.profile

import android.util.Log
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentProfileBinding
import com.google.wishcraft.presentation.extensions.dialogWithActions
import com.google.wishcraft.presentation.extensions.simpleDialog
import com.google.wishcraft.presentation.ui.activities.MainAuthViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment:
    BaseFragment<ProfileViewModel, FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override val viewModel: ProfileViewModel by viewModel()

    private val mainAuthViewModel: MainAuthViewModel by activityViewModel<MainAuthViewModel>()

    override fun initialize() {
        super.initialize()
    }

    override fun initClicks()= with(binding){
        super.initClicks()
        btnLogOut.setOnClickListener {
            dialogWithActions(
                getString(R.string.warning_log_out),
                actionOk = {
                    mainAuthViewModel.logOut()
                },
                actionNo = {

                }
            )

        }
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