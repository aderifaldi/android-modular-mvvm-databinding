package radya.module.auth

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v4.app.Fragment
import com.google.gson.JsonObject
import radya.app.core.base.BaseViewModel
import radya.app.core.util.getJson
import radya.module.auth.login.Login

class AuthViewModel : BaseViewModel() {

    private var response: MediatorLiveData<Login>? = null

    fun getResponse(): LiveData<Login>? {
        return response
    }

    fun login(owner: Fragment, context: Context, jsonObject: JsonObject) {
        response = MediatorLiveData()

        var login: Login?

        AuthRepository().login(context, jsonObject)?.observe(owner, Observer { r ->
            login = getJson(r!!, Login::class.java) as Login
            response?.value = login
        })
    }

}