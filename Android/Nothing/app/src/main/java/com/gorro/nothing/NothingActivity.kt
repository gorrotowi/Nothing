@file:Suppress("MagicNumber", "LongMethod")

package com.gorro.nothing

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.google.firebase.analytics.FirebaseAnalytics
import com.gorro.nothing.databinding.ActivityNothingBinding
import com.gorro.nothing.eggs.GlitchEffect
import com.gorro.nothing.tracking.installReferrerTrack
import com.gorro.nothing.utils.getCompatColor
import com.gorro.nothing.utils.show
import com.squareup.seismic.ShakeDetector
import io.kimo.konamicode.KonamiCode
import pl.droidsonroids.gif.GifDrawable

class NothingActivity : Activity(), ShakeDetector.Listener {

    // No, you clearly don't know who you're talking to, so let me clue you in.
    // I am not in danger, Skyler.
    // I AM the danger!
    // A guy opens his door and gets shot and you think that of me? No. I am the
    // one who knocks!

    private lateinit var binding: ActivityNothingBinding

    private var clickCounter = 0
    private var longClickCounter = 0
    private var shakeCounter = 0

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNothingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        installReferrerTrack()

        val analytics = FirebaseAnalytics.getInstance(this)
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        ShakeDetector(this).also { it.start(sensorManager) }

        KonamiCode.Installer(this)
            .on(this)
            .callback {
                when (shakeCounter) {
                    3 -> {
                        changeGif(R.drawable.dontcomeagain)

                        GlitchEffect.showGlitch(this)
                        binding.imgGif.setOnClickListener {
                            GlitchEffect.showGlitch(this)
                        }
                    }
                    else -> {
                        val browseIntent = Intent(Intent.ACTION_VIEW)
                        browseIntent.data = Uri.parse("http://stackoverflow.com/admin.php")
                        analytics.logEvent(
                            "konami",
                            eventBundle("konami", "konami stack")
                        )
                        startActivity(browseIntent)
                    }
                }
            }
            .install()

        Log.d(
            "This is a simple log",
            "well...this is the log with nothing ;) now go to be happy to another place"
        )
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        binding.txtNothing.setOnClickListener {
            clickCounter += 1
            Log.e("Short Click", "$clickCounter")
            analytics.logEvent(
                "short_click",
                eventBundle("short_click", "simple click")
            )

            if (clickCounter == 10) {
                clickCounter = 0
                analytics.logEvent(
                    "short_click_easter",
                    eventBundle("short_click", "10 click")
                )
                showMessage(getString(R.string.little_track))
            }
        }

        binding.txtNothing.setOnLongClickListener {
            longClickCounter += 1
            Log.e("Long Click", "$longClickCounter")

            if (longClickCounter == 3 && clickCounter == 5) {
                longClickCounter = 0
                clickCounter = 0
                Log.e("segundo cheat", "segundo cheat")
                showMessage(getString(R.string.NothingToastStringTwo))
            }

            false
        }

        binding.imgGif.setOnClickListener {
            when (shakeCounter) {
                100 -> {
                    val browseIntent = Intent(Intent.ACTION_VIEW)
                    browseIntent.data = Uri.parse("https://www.youtube.com/watch?v=ID_L0aGI9bg")

                    analytics.logEvent(
                        "RickRoll",
                        eventBundle(
                            "RickRoll", "RickRoll stack"
                        )
                    )
                    binding.imgGif.show(false)

                    resetCounter()
                    changeText(getString(R.string.NothingString))

                    startActivity(browseIntent)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        binding.snowFallView.show(getCurrentMonthAndDay().isItChristmas())
    }

    override fun hearShake() {
        shakeCounter += 1

        Log.e("ShakeCounter", "$shakeCounter")

        when (shakeCounter) {
            in 0..2 -> changeText(shakeCounter.toString())
            3 -> {
                changeText(getString(R.string.NothingString))
                binding.lyNothing.setBackgroundColor(getCompatColor(R.color.colorPrimaryNight))
                binding.txtNothing.setTextColor(getCompatColor(R.color.colorTextNight))
            }
            4 -> binding.imgGif.show(false)
            5 -> changeText(getString(R.string.stopshake))
            in 6..7 -> changeText(shakeCounter.toString())
            8 -> {
                changeText(getString(R.string.NothingString))
                binding.lyNothing.setBackgroundColor(getCompatColor(R.color.colorPrimary))
            }
            9 -> binding.txtNothing.setTextColor(getCompatColor(R.color.colorText))
            in 10..19 -> changeText(shakeCounter.toString())
            20 -> {
                changeText(getString(R.string.NothingString))
                showMessage(getString(R.string.NothingUpdate), Toast.LENGTH_LONG)
                changeGif(R.drawable.wtfgif)
                //c'mon dude!!! >:(
            }
            25 -> showMessage(getString(R.string.alert_mad), Toast.LENGTH_LONG)
            50 -> showMessage(getString(R.string.alert_understand))
            94 -> binding.imgGif.show(false)
            in 95..99 -> changeText("$shakeCounter")
            100 -> {
                changeGif(R.drawable.rickgetit)
                showMessage(getString(R.string.alert_touch_me))
            }
            101 -> resetCounter()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            val closeDialog = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            sendBroadcast(closeDialog)
        }
    }

    private fun changeText(text: String) {
        binding.txtNothing.text = text
    }

    private fun resetCounter() {
        shakeCounter = 0
    }

    private fun eventBundle(key: String, value: String) = Bundle().apply {
        this.putString(key, value)
    }

    private fun showMessage(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    private fun changeGif(@DrawableRes gitRes: Int) {
        binding.imgGif.show()

        val gifDrawable = GifDrawable(resources, gitRes)
        binding.imgGif.setImageDrawable(gifDrawable)
    }
}
