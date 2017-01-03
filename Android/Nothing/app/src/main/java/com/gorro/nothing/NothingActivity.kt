package com.gorro.nothing

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import io.kimo.konamicode.KonamiCode
import kotlinx.android.synthetic.main.activity_nothing.*

class NothingActivity : Activity() {

    // No, you clearly don't know who you're talking to, so let me clue you in.
    // I am not in danger, Skyler.
    // I AM the danger!
    // A guy opens his door and gets shot and you think that of me? No. I am the
    // one who knocks!

    internal var normal = 0
    internal var largo = 0
    internal var androidVersion: Int = 0

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nothing)

//        val analitycs:FirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        KonamiCode.Installer(this)
                .on(this@NothingActivity)
                .callback {
                    Toast.makeText(this, getString(R.string.just_wait_please), Toast.LENGTH_LONG).show()
                }
                .install()

        Log.d("This is a simple log",
                "well...this is the log whit nothing ;) now go to be happy to another place")
        androidVersion = android.os.Build.VERSION.SDK_INT
        if (androidVersion >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        val tp = Typeface.createFromAsset(assets,
                "fonts/Questrial-Regular.otf")


        txtNothing.typeface = tp

        txtNothing.setOnClickListener {
            normal = normal + 1
            Log.e("Short Click", normal.toString() + "")
            if (normal == 10) {
                Toast.makeText(this, getString(R.string.little_track), Toast.LENGTH_SHORT).show()
                normal = 0
            }
//            if (largo == 4 && normal == 2) {
//                Toast.makeText(this@NothingActivity,
//                        R.string.NothingToastStringOne, Toast.LENGTH_SHORT)
//                        .show()
//                Log.e("primer cheat", "primer cheat")
//                largo = 0
//                normal = 0
//            }
        }

        txtNothing.setOnLongClickListener {
            largo = largo + 1
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            val closeDialog = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            sendBroadcast(closeDialog)
        }
    }
}
