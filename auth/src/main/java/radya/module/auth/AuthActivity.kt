package radya.module.auth

import android.os.Bundle
import kotlinx.android.synthetic.main.auth_activity.*
import radya.app.core.base.BaseActivity
import radya.app.core.util.replaceFragment
import radya.module.auth.login.LoginFragment

class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)
        initView()
    }

    private fun initView() {
        icNavLeft.setImageResource(R.drawable.ic_close_white)

        appBarTitle.text = "LOGIN"
        navLeft.setOnClickListener { onBackPressed() }

        initFragment()
    }

    private fun initFragment() {
        replaceFragment(supportFragmentManager, LoginFragment.newInstance(), R.id.contentFrame, false)
    }

}
