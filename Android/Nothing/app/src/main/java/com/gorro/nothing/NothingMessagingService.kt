package com.gorro.nothing

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NothingMessagingService : FirebaseMessagingService() {

    private val TAG = NothingMessagingService::class.java.simpleName

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "${remoteMessage.from}")
        remoteMessage.notification?.let {
            Log.e(TAG, "${it.title}")
            Log.e(TAG, "${it.body}")
            Log.e(TAG, "${it.tag}")
            Log.e(TAG, "${remoteMessage.data}")
            remoteMessage.data?.map { some ->
                Log.e(TAG, "${some.key} ${some.value}")
            }

            //TODO show alert
        }
    }

}
