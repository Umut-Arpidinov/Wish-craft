package com.google.wishcraft.presentation.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide


fun ImageView.loadImage(url: String?, @DrawableRes drawableRes: Int) {
    Glide.with(this)
        .load(url)
        .placeholder(drawableRes)
        .into(this)
}

