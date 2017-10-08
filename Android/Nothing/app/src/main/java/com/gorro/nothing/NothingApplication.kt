package com.gorro.nothing

import android.app.Application
import com.mobiburn.Mobiburn

/**
 * @author Gorro
 * @since 07/10/17
 */
class NothingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        try {
            Mobiburn.init(this, "dd9c554f-3937-45dc-8e76-90fdbcb6f41f")
        } catch (e:Exception){

        }
    }

}