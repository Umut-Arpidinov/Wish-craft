package com.google.wishcraft.presentation.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.wishcraft.R


fun ImageView.loadImage(url: String?, @DrawableRes drawableRes: Int) {
    Glide.with(this)
        .load(url)
        .apply(RequestOptions()
            .placeholder(drawableRes)
            .error(R.drawable.ic_add)
        )
        .listener(requestListener)

        .into(this)
}

private val requestListener: RequestListener<Drawable> = object : RequestListener<Drawable> {


    override fun onResourceReady(
        resource: Drawable,
        model: Any,
        target: Target<Drawable>?,
        dataSource: DataSource,
        isFirstResource: Boolean
    ): Boolean {
        return false
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>,
        isFirstResource: Boolean
    ): Boolean {
        e?.printStackTrace()
        println("Glide Glide Glide Glide exception")
        return false
    }
}
