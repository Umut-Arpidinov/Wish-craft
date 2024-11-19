package com.google.wishcraft.presentation.ui.search

import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.wishcraft.R
import com.google.wishcraft.common.base.BaseFragment
import com.google.wishcraft.databinding.FragmentSearchBinding
import com.google.wishcraft.domain.models.UserListResponse
import com.google.wishcraft.domain.models.UserResponse
import com.google.wishcraft.domain.models.WishResponse
import com.google.wishcraft.presentation.extensions.hide
import com.google.wishcraft.presentation.extensions.hideKeyboard
import com.google.wishcraft.presentation.extensions.show
import com.google.wishcraft.presentation.utils.InputModeLifeCycleHelper
import com.google.wishcraft.presentation.utils.PEOPLE
import com.google.wishcraft.presentation.utils.WISH
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment :
    BaseFragment<SearchViewModel, FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override val viewModel: SearchViewModel by viewModel()

    var searchJob: Job? = null

    val usersAdapter = UsersAdapter()

    override fun initialize() {
        super.initialize()
        viewLifecycleOwner
            .lifecycle
            .addObserver(
                InputModeLifeCycleHelper(
                    window = activity?.window,
                    InputModeLifeCycleHelper.Mode.ADJUST_NOTHING
                )
            )
        initToolbar()
        handleSearch()
        binding.rvPeople.adapter = usersAdapter
    }

    override fun initClicks()= with(binding){
        super.initClicks()

        usersAdapter.followClicked { id, itemPosition ->
            viewModel.followUser(id)
            usersAdapter.notifyItemChanged(itemPosition)
        }
        usersAdapter.unFollowClicked { id, itemPosition ->
            viewModel.unfollowUser(id)
            usersAdapter.notifyItemChanged(itemPosition)
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.users.observe(viewLifecycleOwner) {
            onUsersReceived(it)
        }
        viewModel.wishes.observe(viewLifecycleOwner){
            onWishReceived(it)
        }
    }

    override fun onError(message: String) {
        super.onError(message)
    }

    override fun onLoading(loading: Boolean) = with(binding)  {
        super.onLoading(loading)
        progress.isVisible = loading
        searchView.isEnabled = !loading
    }


    private fun onUsersReceived(userResponse: UserListResponse?) = with(binding){
        hideKeyboard()
        if (userResponse?.users.isNullOrEmpty()) {
            rvPeople.hide()
        } else {
            usersAdapter.submitList(userResponse?.users)
            rvPeople.show()
        }
    }

    private fun onWishReceived(wishResponse: WishResponse?) = with(binding) {
        hideKeyboard()
        if (wishResponse?.wishes.isNullOrEmpty()) {
            rvWishList.hide()
        } else {
            rvWishList.show()
        }
    }

    private fun handleSearch() = with(binding) {
        chipGroup.setOnCheckedStateChangeListener { group, _ ->
            when (group.checkedChipId) {
                R.id.chipPeople -> {
                    viewModel.setCurrentSearchType(PEOPLE)
                    viewModel.searchCurrentQuery(searchView.query.toString())
                }
                R.id.chipWish -> {
                    viewModel.setCurrentSearchType(WISH)
                    viewModel.searchCurrentQuery(searchView.query.toString())
                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchCurrentQuery(it)
                }
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                searchJob = CoroutineScope(Dispatchers.Main).launch {
                    delay(300)
                    newText?.let {
                        viewModel.searchCurrentQuery(it)
                    }
                }
                return true
            }

        })
    }

    private fun initToolbar() = with(binding) {
        toolbar.apply {
            ivBack.isVisible = false
            ivMenu.isVisible = false
            title.text = getString(R.string.search)
        }
    }

}