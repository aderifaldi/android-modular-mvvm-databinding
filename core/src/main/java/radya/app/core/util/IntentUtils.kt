package radya.app.core.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import radya.app.core.R

fun runSplashDelay(from: Activity, to: Class<*>) {
    Handler().postDelayed({ intentTo(from, to, true) }, 500)
}

fun runSplashDelay(from: Activity, to: Class<*>, bundle: Bundle) {
    Handler().postDelayed({ intentTo(from, to, true, bundle) }, 500)
}

fun intentToMainActivity(from: Activity, mainClass: String) {
    try {
        val myIntent = Intent(from, Class.forName(mainClass))
        from.startActivity(myIntent)
        from.overridePendingTransition(R.anim.left_in, R.anim.left_out)
        from.finish()
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }

}

fun intentTo(from: Activity, to: Class<*>, isFinish: Boolean) {
    val intent = Intent(from, to)
    from.startActivity(intent)
    from.overridePendingTransition(R.anim.left_in, R.anim.left_out)
    if (isFinish) {
        from.finish()
    }
}

fun intentTo(from: Activity, to: Class<*>, isFinish: Boolean, data: Bundle?) {
    if (data != null) {
        val intent = Intent(from, to)
        intent.putExtras(data)

        from.startActivity(intent)
        from.overridePendingTransition(R.anim.left_in, R.anim.left_out)
        if (isFinish) {
            from.finish()
        }
    }
}

fun backTo(from: Activity, to: Class<*>, isFinish: Boolean) {

    val intent = Intent(from, to)
    from.startActivity(intent)
    from.overridePendingTransition(R.anim.left_back_in, R.anim.left_back_out)
    if (isFinish) {
        from.finish()
    }
}

fun backTo(from: Activity, to: Class<*>, isFinish: Boolean, data: Bundle?) {
    if (data != null) {
        val intent = Intent(from, to)
        intent.putExtras(data)

        from.startActivity(intent)
        from.overridePendingTransition(R.anim.left_in, R.anim.left_out)
        if (isFinish) {
            from.finish()
        }
    }
}

fun backAnimation(activity: Activity) {
    activity.overridePendingTransition(R.anim.left_back_in, R.anim.left_back_out)
}
