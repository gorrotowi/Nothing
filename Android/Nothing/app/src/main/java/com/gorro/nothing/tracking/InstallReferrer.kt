package com.gorro.nothing.tracking

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerClient.InstallReferrerResponse.OK
import com.android.installreferrer.api.InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED
import com.android.installreferrer.api.InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.google.firebase.analytics.FirebaseAnalytics

const val TAG = "NothingTracking"

fun Context.installReferrerTrack() {
    val analytics = FirebaseAnalytics.getInstance(this)

    val referrerClient = InstallReferrerClient.newBuilder(this).build()
    referrerClient.startConnection(object : InstallReferrerStateListener {
        override fun onInstallReferrerSetupFinished(responseCode: Int) {
            when (responseCode) {
                OK -> {
                    val response: ReferrerDetails = referrerClient.installReferrer
                    val referrerUrl: String = response.installReferrer

                    analytics.logEvent("install_referrer", bundleFromReferrer(referrerUrl))
                }
                FEATURE_NOT_SUPPORTED -> Log.e(TAG, "onInstallReferrerSetupFinished: Not supported")
                SERVICE_UNAVAILABLE -> Log.e(TAG, "onInstallReferrerSetupFinished: Unavailble")
            }
        }

        override fun onInstallReferrerServiceDisconnected() {
            //Not implemented
        }
    })
}

private fun bundleFromReferrer(referrerUrl: String) =
    referrerUrl.split("&").fold(Bundle()) { acc, nextParam ->
        val keyValue = nextParam.split("=")
        acc.putString(
            keyValue.first().replace("utm_", ""),
            keyValue.last().replace("utm_", "")
        )
        acc
    }
