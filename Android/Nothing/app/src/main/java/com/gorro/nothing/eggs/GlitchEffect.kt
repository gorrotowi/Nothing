@file:Suppress("MagicNumber")
package com.gorro.nothing.eggs

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.widget.ImageView
import androidx.annotation.Nullable
import java.io.ByteArrayOutputStream
import java.util.*


object GlitchEffect {
    private val RANDOM = Random()
    private const val BITMAP_COUNT = 4
    private const val JPEG_QUALITY = 33
    private const val JPEG_CORRUPTION_COUNT = 5
    private const val JPEG_HEADER_SIZE = 100
    private const val ANIM_FRAME_DURATION_MAX = 150
    private const val ANIM_FRAME_DURATION_MIN = 50

    private var sMainHandler: Handler? = null

    private val mainHandler: Handler
        get() {
            if (sMainHandler == null) sMainHandler = Handler(Looper.getMainLooper())
            return sMainHandler as Handler
        }

    fun showGlitch(activity: Activity) {
        val bitmap = captureWindow(activity)
        val corruptedBitmaps = makeCorruptedBitmaps(bitmap, BITMAP_COUNT)
        showAnimation(activity, corruptedBitmaps)
    }

    private fun captureWindow(activity: Activity): Bitmap {
        val root = activity.window.decorView.rootView
        val screenshot = Bitmap.createBitmap(root.width, root.height, Bitmap.Config.RGB_565)
        val canvas = Canvas(screenshot)
        root.draw(canvas)
        return screenshot
    }

    private fun makeCorruptedBitmaps(bitmap: Bitmap, count: Int): Array<Bitmap?> {
        val bos = ByteArrayOutputStream(1024)
        val res = arrayOfNulls<Bitmap>(count)
        bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, bos)
        val data = bos.toByteArray()
        var i = 0
        while (i < count) {
            val corrupted = getCorruptedArray(data)
            val decoded = decodeJpg(corrupted)
            if (decoded != null) {
                res[i] = decoded
                i++
            }
        }
        return res
    }

    private fun getCorruptedArray(source: ByteArray): ByteArray {
        val res = source.clone()
        for (i in 0 until JPEG_CORRUPTION_COUNT) {
            val idx = RANDOM.nextInt(res.size - JPEG_HEADER_SIZE) + JPEG_HEADER_SIZE
            res[idx] = (res[idx] + RANDOM.nextInt(3).toByte()).toByte()
        }
        return res
    }

    @Nullable
    private fun decodeJpg(data: ByteArray): Bitmap? {
        val options = BitmapFactory.Options().also {
            it.inPreferredConfig = Bitmap.Config.RGB_565
            it.inSampleSize = 2
        }
        return BitmapFactory.decodeByteArray(data, 0, data.size, options)
    }

    private fun showAnimation(activity: Activity, bitmaps: Array<Bitmap?>) {
        val windowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val layoutParams = LayoutParams(LayoutParams.FIRST_SUB_WINDOW)
        val decorView = activity.window.decorView
        layoutParams.width = decorView.width
        layoutParams.height = decorView.height
        layoutParams.format = PixelFormat.RGBA_8888
        layoutParams.flags = LayoutParams.FLAG_LAYOUT_IN_SCREEN or LayoutParams.FLAG_HARDWARE_ACCELERATED
        layoutParams.token = decorView.rootView.windowToken

        val imageView = ImageView(activity)
        val animationDrawable = AnimationDrawable()
        animationDrawable.isOneShot = true
        var totalDuration = 0L
        val resources = activity.resources
        for (bitmap in bitmaps) {
            val duration = RANDOM.nextInt(ANIM_FRAME_DURATION_MAX - ANIM_FRAME_DURATION_MIN) + ANIM_FRAME_DURATION_MIN
            totalDuration += duration
            animationDrawable.addFrame(BitmapDrawable(resources, bitmap), duration)
        }
        imageView.setImageDrawable(animationDrawable)
        windowManager.addView(imageView, layoutParams)
        animationDrawable.start()
        mainHandler.postDelayed({ windowManager.removeView(imageView) }, totalDuration)
    }
}
