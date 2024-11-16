package com.google.wishcraft.presentation.ui.profile

import androidx.core.view.isVisible
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentProfileBinding
import com.google.wishcraft.presentation.extensions.dialogWithActions
import com.google.wishcraft.presentation.extensions.loadImage
import com.google.wishcraft.presentation.ui.activities.MainAuthViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<ProfileViewModel, FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override val viewModel: ProfileViewModel by viewModel()

    private val mainAuthViewModel: MainAuthViewModel by activityViewModel<MainAuthViewModel>()

    private val adapter = MyWishesAdapter()

    override fun initialize() {
        super.initialize()
        binding.rvWishList.adapter = adapter
    }

    override fun initClicks() = with(binding) {
        super.initClicks()
        setToolbar()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.user.observe(viewLifecycleOwner) {
            it.user?.staticObject?.let {
                binding.ivAva.loadImage(it, R.drawable.ic_launcher_background)
            }
            binding.tvUsername.text = it.user?.username
            binding.tvFollowersValue.text = it?.user?.followers?.size?.toString() ?: "0"
            binding.tvFollowingValue.text = it?.user?.following?.size?.toString() ?: "0"
            binding.tvWishesValue.text = it?.user?.wishes?.size?.toString() ?: "0"
            adapter.submitList(it?.user?.wishes)
        }
    }

    override fun onError(message: String) {
        super.onError(message)
    }

    override fun onLoading(loading: Boolean) {
        super.onLoading(loading)
        binding.progress.isVisible = loading
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            title.text = "Профиль"
            ivMenu.setOnClickListener {
                dialogWithActions(
                    getString(R.string.warning_log_out),
                    actionOk = {
                        mainAuthViewModel.logOut()
                    },
                    actionNo = {}
                )
            }
        }
    }


}