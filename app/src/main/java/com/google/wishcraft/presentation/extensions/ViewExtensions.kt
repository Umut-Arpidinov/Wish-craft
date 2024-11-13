package com.google.wishcraft.presentation.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.visible(animate: Boolean = false, duration: Long = 300) {
    if (animate) {
        animate().alpha(1f).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}


fun View.gone(animate: Boolean = false, duration: Long = 300) {
    hide(View.GONE, animate, duration)

}

private fun View.hide(hidingStrategy: Int, animate: Boolean = true, duration: Long = 300) {
    if (animate) {
        animate().alpha(0f).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = hidingStrategy
            }
        })
    } else {
        visibility = hidingStrategy
    }
}
