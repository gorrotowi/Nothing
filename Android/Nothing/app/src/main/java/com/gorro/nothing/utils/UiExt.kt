package com.gorro.nothing.utils

import android.content.Context
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getCompatColor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

fun View.show(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}