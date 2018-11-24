package radya.module.assignment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import radya.app.core.data.remote.RetrofitCallback
import radya.app.core.data.remote.callApi
import radya.app.core.data.remote.apiRequest
import radya.app.core.data.remote.httpClient

class AssignmentReposiitory {
    fun listAssignment(context: Context, from: String, to: String, status: Int): LiveData<String>? {
        val response: MediatorLiveData<String>? = MediatorLiveData()

        val httpClient = httpClient(context, true)
        val call = apiRequest<AssignmentService>(httpClient).GetAssignmentAll(from, to, status)

        callApi(context, call, object : RetrofitCallback {
            override fun onFinishRequest(r: String?) {
                response?.value = r
            }
        })

        return response
    }
}