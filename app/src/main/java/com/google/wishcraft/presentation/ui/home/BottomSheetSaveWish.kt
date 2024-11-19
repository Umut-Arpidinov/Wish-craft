package com.google.wishcraft.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.wishcraft.common.base.BaseBottomSheet
import com.google.wishcraft.common.base.BottomSheetType
import com.google.wishcraft.databinding.BottomSheetSaveBinding
import com.google.wishcraft.presentation.utils.CustomDialog

class BottomSheetSaveWish :
    BaseBottomSheet<BottomSheetSaveBinding>(BottomSheetSaveBinding::inflate) {

    override fun bottomSheetType(): BottomSheetType =
        BottomSheetType.WRAP_CONTENT


    private var onCameraClickListener: (() -> Unit)? = null
    private var onGalleryClickListener: (() -> Unit)? = null


    override fun initialize() {
        super.initialize()
    }

    override fun initClicks() = with(binding) {
        super.initClicks()

        llGallery.setOnClickListener {
            dismiss()
            onGalleryClickListener?.invoke()

        }

        llCamera.setOnClickListener {
            dismiss()
            onCameraClickListener?.invoke()

        }


        return@with
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }


    fun onSaveClicked(listener: () -> Unit) {
        onCameraClickListener = listener
    }

    fun onShareClicked(listener: () -> Unit) {
        onGalleryClickListener = listener
    }


    fun showBottomSheet(
        manager: FragmentManager,
        onSaveClick: (() -> Unit)? = null,
        onShareClick: (() -> Unit)? = null
    ) {
        if(!isAdded){
            onCameraClickListener = onSaveClick
            onGalleryClickListener = onShareClick
            this.show(manager, null)
        }
    }


    companion object {
        private const val ARG_MESSAGE = "dialog_message"
        private const val DIALOG_TYPE = "dialog_type"
        fun newInstance(
        ): BottomSheetSaveWish {
            val sheet = BottomSheetSaveWish()
            return sheet
        }
    }

}