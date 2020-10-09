package com.gorro.nothing.utils

import android.content.Context
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Obtain a color with the [ContextCompat] helper class. This is just
 * a shorthand for the actual function.
 *
 * @param colorRes is an [Int] of [ColorRes]
 * @return [Int] of [ColorInt]
 */
fun Context.getCompatColor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

/**
 * Show or hide the [View] where this is called. Simply changes the [View.setVisibility]
 * based on the param.
 *
 * @param visible defaults to true. Which means you want to show the View. Pass a false and it will
 * make the visibility [View.GONE]
 */
fun View.show(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
