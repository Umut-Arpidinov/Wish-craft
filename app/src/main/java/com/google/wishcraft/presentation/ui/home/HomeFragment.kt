package com.google.wishcraft.presentation.ui.home

import androidx.core.view.isVisible
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentHomeBinding
import com.google.wishcraft.presentation.extensions.hide
import com.google.wishcraft.presentation.extensions.simpleDialog
import org.koin.androidx.navigation.koinNavGraphViewModel

class HomeFragment :
    BaseFragment<HomeViewModel, FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by koinNavGraphViewModel(R.id.nav_home)


    private val bottomSheet = BottomSheetSaveWish.newInstance()

    private val wishListAdapter = WishListAdapter()
    private var itemDecoration = MarginItemDecoration()
    override fun initialize() = with(binding) {
        super.initialize()
        rvWishList.adapter = wishListAdapter
        rvWishList.addItemDecoration(itemDecoration)
        initToolbar()
        initSwipeRefresh()
        return@with
    }

    override fun initClicks() = with(binding) {
        super.initClicks()
        wishListAdapter.onSaveClickListener {
            bottomSheet.showBottomSheet(
                childFragmentManager,
                onShareClick = {

                },
                onSaveClick = {
                    it.id?.let {
                        viewModel.copyWish(it)
                    }

                }
            )
        }

        swipe.setOnRefreshListener {
            viewModel.refreshWish {
                swipe.isRefreshing = false
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.wishes.observe(viewLifecycleOwner) {
            wishListAdapter.submitList(it.wishes)
        }

        viewModel.wishCopyState.observe(viewLifecycleOwner) {
            simpleDialog("Жаление была добавлено в ваш список")
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

    private fun initSwipeRefresh()  {
        binding.swipe.apply {
            setColorSchemeResources(R.color.sea_blue_2)
            setProgressViewOffset(true,0,250)
        }

    }


    private fun initToolbar() = with(binding) {
        toolbar.apply {
            ivBack.hide()
            ivMenu.hide()
            title.text = "Актуальные вишлисты"
        }
    }

}