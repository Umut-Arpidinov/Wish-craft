package com.google.wishcraft.presentation.extensions

import androidx.fragment.app.Fragment
import com.google.wishcraft.presentation.utils.CustomDialog
import com.google.wishcraft.presentation.utils.DIALOG_WITH_ACTION
import com.google.wishcraft.presentation.utils.SIMPLE_DIALOG

fun Fragment.simpleDialog(message: String) {
    CustomDialog.newInstance(message, SIMPLE_DIALOG)
        .showDialog(childFragmentManager)
}
fun Fragment.dialogWithActions(
    message: String,
    actionOk: () -> Unit,
    actionNo: () -> Unit
) {
    CustomDialog.newInstance(message, DIALOG_WITH_ACTION)
        .showDialogWithActions(
            childFragmentManager,
            actionOk, actionNo
        )
}