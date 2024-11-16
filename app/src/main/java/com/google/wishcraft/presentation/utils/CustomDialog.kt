package com.google.wishcraft.presentation.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.gson.annotations.Until
import com.google.wishcraft.databinding.DialogBaseBinding
import com.google.wishcraft.databinding.DialogBinding
import com.google.wishcraft.presentation.extensions.hide
import com.google.wishcraft.presentation.extensions.show

class CustomDialog : DialogFragment() {

    private var _binding: DialogBinding? = null
    private val binding: DialogBinding get() = _binding!!

    private var onPositiveClickListener: (() -> Unit)? = null
    private var onNegativeClickListener: (() -> Unit)? = null

    private val dialogType: String? get() = arguments?.getString(DIALOG_TYPE)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        private const val ARG_MESSAGE = "dialog_message"
        private const val DIALOG_TYPE = "dialog_type"
        fun newInstance(
            message: String,
            dialogType: String
        ): CustomDialog {
            val dialog = CustomDialog()
            val args = Bundle()
            args.putString(ARG_MESSAGE, message)
            args.putString(DIALOG_TYPE, dialogType)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onStart() {
        super.onStart()
        dialogViewParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARG_MESSAGE)?.let {
            binding.tvMessage.text = it
        }
        setDialogViewsByType()
        btnOk.setOnClickListener {
            dismiss()
        }

        btnYes.setOnClickListener {
            onPositiveClickListener?.invoke()
            dismiss()
        }
        btnNo.setOnClickListener {
            onNegativeClickListener?.invoke()
            dismiss()
        }

    }

    private fun setDialogViewsByType() = with(binding) {
        when (dialogType) {
            SIMPLE_DIALOG -> {
                llActions.hide()
                btnOk.show()
            }

            DIALOG_WITH_ACTION -> {
                llActions.show()
                btnOk.hide()
            }
        }
    }
    private fun dialogViewParams(){


        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    }

    fun showDialog(manager: FragmentManager) {
        if (!isAdded) {
            show(manager, null)
        }
    }

    fun showDialogWithActions(
        manager: FragmentManager,
        actionOk: () -> Unit,
        actionNo: () -> Unit
    ){
        if (!isAdded) {
            onPositiveClickListener = actionOk
            onNegativeClickListener = actionNo
            show(manager, null)
        }
    }
}