package com.gorro.nothing

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.hardware.SensorManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.gorro.nothing.eggs.GlitchEffect
import com.squareup.seismic.ShakeDetector
import io.kimo.konamicode.KonamiCode
import kotlinx.android.synthetic.main.activity_nothing.*
import pl.droidsonroids.gif.GifDrawable
import java.util.*

class NothingActivity : Activity(), ShakeDetector.Listener {

    // No, you clearly don't know who you're talking to, so let me clue you in.
    // I am not in danger, Skyler.
    // I AM the danger!
    // A guy opens his door and gets shot and you think that of me? No. I am the
    // one who knocks!

    private var normal = 0
    private var largo = 0
    private var androidVersion: Int = 0
    private var shakeCounter = 0
    private var shakeCounterHundred = 0

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nothing)

        val tp = Typeface.createFromAsset(assets, "fonts/Questrial-Regular.otf")
        txtNothing.typeface = tp

        val analytics = FirebaseAnalytics.getInstance(this)
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val shakeDetector = ShakeDetector(this)
        shakeDetector.start(sensorManager)

        KonamiCode.Installer(this)
                .on(this@NothingActivity)
                .callback {
                    when (shakeCounter) {
                        3 -> {
                            imgGif.visibility = View.VISIBLE
                            val gifDrawable = GifDrawable(resources, R.drawable.dontcomeagain)
                            imgGif.setImageDrawable(gifDrawable)
//                            if (isWhiteBackground) {
                            GlitchEffect.showGlitch(this)
                            imgGif?.setOnClickListener {
                                GlitchEffect.showGlitch(this)
                            }
//                            }
                        }
                        else -> {
                            val browseIntent = Intent(Intent.ACTION_VIEW)
                            browseIntent.data = Uri.parse("http://stackoverflow.com/admin.php")
                            val params = Bundle()
                            params.putString("konami", "konami stack")
                            analytics.logEvent("konami", params)
                            startActivity(browseIntent)
                        }
                    }
                }
                .install()

        Log.d("This is a simple log",
                "well...this is the log with nothing ;) now go to be happy to another place")
        androidVersion = Build.VERSION.SDK_INT
        if (androidVersion >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            try {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            } catch (e: Exception) {
                Log.e("Error SYSUI", "Error", e)
            }
        }

        txtNothing.setOnClickListener {
            normal += 1
            Log.e("Short Click", normal.toString() + "")
            val paramsShort = Bundle()
            paramsShort.putString("short_click", "simple click")
            analytics.logEvent("short_click", paramsShort)
            if (normal == 10) {
                val paramsShortEaster = Bundle()
                paramsShortEaster.putString("short_click", "10 click")
                analytics.logEvent("short_click_easter", paramsShortEaster)
                Toast.makeText(this, getString(R.string.little_track), Toast.LENGTH_SHORT).show()
                normal = 0
            }
        }

        txtNothing.setOnLongClickListener {
            largo += 1
            Log.e("Long Click", largo.toString() + "")
            if (largo == 3 && normal == 5) {
                Log.e("segundo cheat", "segundo cheat")
                Toast.makeText(this@NothingActivity,
                        R.string.NothingToastStringTwo, Toast.LENGTH_SHORT)
                        .show()
                largo = 0
                normal = 0
            }

            false
        }

        imgGif.setOnClickListener {
            when (shakeCounterHundred) {
                100 -> {
                    val browseIntent = Intent(Intent.ACTION_VIEW)
                    browseIntent.data = Uri.parse("https://www.youtube.com/watch?v=ID_L0aGI9bg")
                    val params = Bundle()
                    params.putString("RickRoll", "RickRoll stack")
                    analytics.logEvent("RickRoll", params)
                    imgGif.visibility = View.GONE
                    shakeCounter = 0
                    shakeCounterHundred = 0
                    txtNothing.text = getString(R.string.NothingString)
                    startActivity(browseIntent)
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        if (needToSnow(getCurrentMonthAndDay())) {
            snowFallView?.visibility = View.VISIBLE
        } else {
            snowFallView?.visibility = View.GONE
        }
    }

    override fun hearShake() {
        shakeCounter += 1
        shakeCounterHundred += 1
        Log.e("Counter", "$shakeCounter")
        Log.e("Counter", "$shakeCounterHundred")
        when (shakeCounter) {
            in 0..2 -> {
                txtNothing.text = "$shakeCounter"
            }
            3 -> {
                txtNothing.text = getString(R.string.NothingString)
                txtNothing.setTextColor(getCompatColor(R.color.colorTextNight))
                lyNothing.setBackgroundColor(getCompatColor(R.color.colorPrimaryNight))
            }
            4 -> {
                imgGif.visibility = View.GONE
            }
            5 -> {
                txtNothing.text = getString(R.string.stopshake)
            }
            in 5..7 -> txtNothing.text = "$shakeCounter"
            8 -> {
                txtNothing.text = getString(R.string.NothingString)
                lyNothing.setBackgroundColor(getCompatColor(R.color.colorPrimary))
            }
            9 -> txtNothing.setTextColor(getCompatColor(R.color.colorText))
            in 10..19 -> txtNothing.text = "$shakeCounter"
            20 -> {
                txtNothing.text = getString(R.string.NothingString)
                Toast.makeText(this, getString(R.string.NothingUpdate), Toast.LENGTH_LONG).show()
                imgGif.visibility = View.VISIBLE
                val gifDrawable = GifDrawable(resources, R.drawable.wtfgif)
                imgGif.setImageDrawable(gifDrawable)
                //c'mon dude!!! >:(
            }
            25 -> {
                Toast.makeText(this, "I'm mad now!", Toast.LENGTH_LONG).show()
            }
        }
        when (shakeCounterHundred) {
            50 -> Toast.makeText(this, "You don't understand right?", Toast.LENGTH_SHORT).show()
            94 -> imgGif.visibility = View.GONE
            in 95..99 -> txtNothing.text = "$shakeCounterHundred"
            100 -> {
                imgGif.visibility = View.VISIBLE
                val gifDrawable = GifDrawable(resources, R.drawable.rickgetit)
                imgGif.setImageDrawable(gifDrawable)
                Toast.makeText(this, "Ok...touch me...", Toast.LENGTH_SHORT).show()
            }
            101 -> {
                shakeCounterHundred = 0
                shakeCounter = 0
            }
        }
    }

    private fun getCurrentMonthAndDay(): Pair<Int, Int> {
        val calendar = Calendar.getInstance()
        Log.e("Calendar", calendar.toString())
        return Pair(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    }

    private fun needToSnow(monthAndDay: Pair<Int, Int>): Boolean {
        return monthAndDay.first == 11 && (monthAndDay.second == 24 || monthAndDay.second == 25)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            val closeDialog = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            sendBroadcast(closeDialog)
        }
    }

    private fun Context.getCompatColor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)
}
