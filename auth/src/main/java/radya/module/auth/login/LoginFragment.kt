package radya.module.auth.login


import adr.core.data.constant.Key
import adr.core.data.local.saveBoolean
import adr.core.data.local.saveString
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.login_fragment.*
import radya.app.core.base.BaseFragment
import radya.app.core.helper.Sha256
import radya.app.core.util.*
import radya.module.auth.AuthViewModel
import radya.module.auth.R
import java.security.NoSuchAlgorithmException

/**
 * Created by aderifaldi on 2018-02-06.
 */

class LoginFragment : BaseFragment() {

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    private var viewModel: AuthViewModel? = null
    private var isPasswordShow: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
    }

    private fun initView() {
        initLoading(swipeRefreshLayout)

        btnLogin.setOnClickListener { login(swipeRefreshLayout, edtEmailAddress, edtPassword) }
        icShowPassword.setOnClickListener { passwordShowValidation(edtPassword, icShowPassword) }

        btnRegister.setOnClickListener { activity?.let { showToast(it, "Open Register Page!") } }
    }

    private fun passwordShowValidation(edtPassword: EditText, icShowPassword: ImageView) {
        val password: String = edtPassword.text.toString()

        if (!TextUtils.isEmpty(password)) {
            if (!isPasswordShow) {
                EditTextUtils.showPassword(edtPassword)
                isPasswordShow = true
                icShowPassword.setImageResource(R.drawable.ic_hide_password)
            } else {
                EditTextUtils.hidePassword(edtPassword)
                isPasswordShow = false
                icShowPassword.setImageResource(R.drawable.ic_show_password)
            }
        }
    }

    private fun initLoading(swipeRefreshLayout: SwipeRefreshLayout) {
        activity?.let { setupLoading(it, swipeRefreshLayout) }
    }

    private fun login(loading: SwipeRefreshLayout, edtEmailAddress: EditText, edtPassword: EditText) {
        val emailAddress = edtEmailAddress.text.toString()
        val password = edtPassword.text.toString()

        var encriptPassword: String? = ""

        try {
            encriptPassword = Sha256.hexString(password)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        if (TextUtils.isEmpty(emailAddress)) {
            edtEmailAddress.error = getString(R.string.errorMessageEmptyEmailAddress)
        } else if (TextUtils.isEmpty(password)) {
            edtPassword.error = getString(R.string.errorMessageEmptyPassword)
        } else if (password.length < Key.MIN_PASSWORD) {
            edtPassword.error = getString(R.string.errorMessageMinimumPassword)
        } else {
            showLoading(loading)

            val jsonObject = JsonObject()
            jsonObject.addProperty("username", emailAddress)
            jsonObject.addProperty("password", encriptPassword)

            activity?.let { viewModel?.login(this, it, jsonObject) }
            viewModel?.getResponse()?.observe(this, Observer { apiResponse ->
                dismissLoading(loading)

                if (apiResponse != null) {
                    activity?.let { saveBoolean(it, Key.IS_LOGIN, true) }
                    activity?.let { saveString(it, Key.AUTH_TOKEN, apiResponse.data.login_token) }

                    activity?.let { intentToMainActivity(it, "radya.app.androidmodular.feature.MainActivity") }
                }

            })

        }

    }

}
