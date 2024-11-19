package com.google.wishcraft.presentation.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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



fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
