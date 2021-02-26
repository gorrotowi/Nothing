package com.gorro.nothing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView

class DeepLinkActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent()
    }

    private fun handleIntent() {


        if (!intent.isActionView()) {
            setContentView(R.layout.activity_nope)
        } else {
            setContentView(R.layout.activity_deeplink)

            val number = intent.data?.host?.toInt() ?: 0
            val imageView = findViewById<ImageView>(R.id.contentImage)
            when (number) {
                1 -> imageView.setImageResource(R.drawable.reverse)
                4 -> imageView.setImageResource(R.drawable.add_four)
                else -> imageView.setImageResource(R.drawable.skip)
            }

        }
    }

    private fun Intent.isActionView(): Boolean = intent.action == Intent.ACTION_VIEW
}