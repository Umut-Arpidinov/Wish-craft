package com.google.wishcraft.presentation.extensions

import android.view.Window


fun Window.getSoftInputMode(): Int {
    return attributes.softInputMode
}

