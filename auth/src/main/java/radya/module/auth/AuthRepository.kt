package radya.module.auth

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import com.google.gson.JsonObject
import radya.app.core.data.remote.RetrofitCallback
import radya.app.core.data.remote.callApi
import radya.app.core.data.remote.apiRequest
import radya.app.core.data.remote.httpClient

class AuthRepository {
    fun login(context: Context, jsonObject: JsonObject): LiveData<String>? {
        val response: MediatorLiveData<String>? = MediatorLiveData()

        val httpClient = httpClient(context, false)
        val call = apiRequest<AuthService>(httpClient).Login(jsonObject)

        callApi(context, call, object : RetrofitCallback {
            override fun onFinishRequest(r: String?) {
                response?.value = r
            }
        })

        return response
    }
}