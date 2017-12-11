package com.gorro.nothing

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * @author gorrotowi
 * @since 10/12/17.
 */
class NothingFirebaseIDService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val token = FirebaseInstanceId.getInstance()?.token
        if (BuildConfig.DEBUG) {
            Log.e("TokenFb", "$token")
        }
    }
}