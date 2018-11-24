package radya.app.androidmodular

import adr.core.data.constant.Key
import adr.core.data.local.getBoolean
import android.app.Application


/**
 * Created by aderifaldi on 2018-01-18.
 */

class MyApplication : Application() {

    val isLogin: Boolean
        get() = getBoolean(instance!!, Key.IS_LOGIN)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @get:Synchronized
        var instance: MyApplication? = null
            private set
    }

}
