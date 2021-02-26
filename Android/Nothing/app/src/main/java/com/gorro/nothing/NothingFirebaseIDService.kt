package com.gorro.nothing

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessagingService

/**
 * @author gorrotowi
 * @since 10/12/17.
 */

class NothingFirebaseIDService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (BuildConfig.DEBUG) {
                    Log.e("TOKEN FB", "${task.result}")
                }
            }
        }
    }
}
