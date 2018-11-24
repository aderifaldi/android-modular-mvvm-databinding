package radya.app.core.util

import android.content.Context
import android.widget.Toast


/**
 * Created by aderifaldi on 2018-02-06.
 */
fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
