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
import com.google.firebase.analytics.FirebaseAnalytics
import com.squareup.seismic.ShakeDetector
import io.kimo.konamicode.KonamiCode
import kotlinx.android.synthetic.main.activity_nothing.*

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
                    //                    Toast.makeText(this, getString(R.string.just_wait_please), Toast.LENGTH_LONG).show()
                    val browseIntent: Intent = Intent(Intent.ACTION_VIEW)
                    browseIntent.data = Uri.parse("http://stackoverflow.com/admin.php")
                    val params: Bundle = Bundle()
                    params.putString("konami", "konami stack")
                    analytics.logEvent("konami", params)
                    startActivity(browseIntent)
                }
                .install()

        Log.d("This is a simple log",
                "well...this is the log with nothing ;) now go to be happy to another place")
        androidVersion = android.os.Build.VERSION.SDK_INT
        if (androidVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        txtNothing.setOnClickListener {
            normal += 1
            Log.e("Short Click", normal.toString() + "")
            val params: Bundle = Bundle()
            params.putString("Short Click", "simple click")
            analytics.logEvent("Short Click", params)
            if (normal == 10) {
                val params: Bundle = Bundle()
                params.putString("Short Click", "10 click")
                analytics.logEvent("Short Click easter", params)
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

    }

    override fun hearShake() {
        shakeCounter += 1
        when (shakeCounter) {
            in 0..2 -> txtNothing.text = "$shakeCounter"
            3 -> {
                txtNothing.text = getString(R.string.NothingString)
                txtNothing.setTextColor(resources.getColor(R.color.colorTextNight))
                lyNothing.setBackgroundColor(resources.getColor(R.color.colorPrimaryNight))
            }
            5 -> {
                txtNothing.text = getString(R.string.stopshake)
            }
            in 5..7 -> txtNothing.text = "$shakeCounter"
            8 -> {
                txtNothing.text = getString(R.string.NothingString)
                lyNothing.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            }
            9 -> txtNothing.setTextColor(resources.getColor(R.color.colorText))
            in 10..19 -> txtNothing.text = "$shakeCounter"
            20 -> {
                txtNothing.text = getString(R.string.NothingString)
                Toast.makeText(this, getString(R.string.NothingUpdate), Toast.LENGTH_LONG).show()
                shakeCounter = 0
                //c'mmon dude!!! >:(
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            val closeDialog = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            sendBroadcast(closeDialog)
        }
    }
}
