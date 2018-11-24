package radya.app.androidmodular.feature

import adr.core.data.constant.Key
import adr.core.data.local.saveBoolean
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.main_activity.*
import radya.app.androidmodular.MyApplication
import radya.app.androidmodular.R
import radya.app.core.util.replaceFragment
import radya.app.core.util.runSplashDelay
import radya.module.assignment.list.AssignmentListFragment
import radya.module.auth.AuthActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initView()
    }

    private fun initView() {
        appBarTitle.text = "MY ASSIGNMENT"
        navLeft.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        if (MyApplication.instance!!.isLogin) {
            replaceFragment(
                supportFragmentManager,
                AssignmentListFragment.newInstance(),
                R.id.contentFrame,
                false
            )
        } else {
            runSplashDelay(this, AuthActivity::class.java)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        saveBoolean(this, Key.IS_LOGIN, false)
    }

}
