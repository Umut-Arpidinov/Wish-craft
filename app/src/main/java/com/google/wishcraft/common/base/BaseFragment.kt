package com.google.wishcraft.common.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.wishcraft.common.uitls.ErrorConverter
import org.koin.android.ext.android.inject

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {


    protected val errorConverter: ErrorConverter by inject()

    private lateinit var authenticationListener: AuthenticationListener


    private var loading = false

    private var _binding: VB? = null
    protected val binding
        get() = _binding!!

    protected abstract val viewModel: VM


    override fun onAttach(context: Context) {
        super.onAttach(context)
        authenticationListener = context as? AuthenticationListener
            ?: throw IllegalStateException("Host Activity must implement AuthenticationListener")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater.invoke(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoadingState()
        initialize()
        initClicks()
        observeViewModel()
        observeErrorState()
    }

    protected open fun initialize() {

    }

    protected open fun initClicks() {}

    protected open fun observeViewModel() {

    }

    private fun observeErrorState() {
        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                if (it.throwable is NoSuchElementException)
                    return@observe
                onError(errorConverter.convert(it.throwable))
                onError(it.throwable)
                if (errorConverter.isAuthenticationError(it.throwable)) {
                    authenticationListener.logout()
                }
            }
        }
    }


    private fun observeLoadingState() {
        viewModel.loading.observe(viewLifecycleOwner) {
            val anyLoading = viewModel.loading.value == true
            if (loading != anyLoading) {
                loading = anyLoading
                onLoading(loading)
            }
        }
    }


    protected open fun onLoading(loading: Boolean) {
        this.loading = loading

    }

    protected open fun onError(throwable: Throwable) {}

    protected open fun onError(message: String) {}


    protected fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), message, length).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    interface AuthenticationListener {
        fun logout()
    }


}
