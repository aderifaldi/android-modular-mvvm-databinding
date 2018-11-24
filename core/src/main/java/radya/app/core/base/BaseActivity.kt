package radya.app.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.*

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}