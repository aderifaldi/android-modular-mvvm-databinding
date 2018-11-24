package radya.app.core.data.remote

import adr.core.data.constant.ApiCode
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import radya.app.core.data.BaseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

fun <T> callApi(context: Context, call: Call<T>, callback: RetrofitCallback) {
    var apiResponse: String? = ""

    var alertMessage: String?
    var isError: Boolean?
    var responseCode: Int?
    var alertCode: Int?

    var baseModel: BaseModel?

    call.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {

            responseCode = response.code()

            if (responseCode == ApiCode.INTERNAL_SERVER_ERROR) { //500
                alertMessage = "Internal server error"
                showToast(context, alertMessage)
                apiResponse = ""
            } else if (responseCode == ApiCode.SERVICE_NOT_FOUND) { //404
                alertMessage = "Service not found"
                showToast(context, alertMessage)
                apiResponse = ""
            } else {

                /* TODO: convert string response to base model
                 * that i used for api in this project
                 * you can modify this handler
                 */

                baseModel = getJson(response.body().toString(), BaseModel::class.java) as BaseModel?

                if (baseModel != null) {
                    alertMessage = baseModel?.alert?.message
                    alertCode = baseModel?.alert?.code
                    isError = baseModel?.isError

                    if (response.isSuccessful) {
                        try {
                            if (isError == false) {
                                if (alertCode == ApiCode.SUCCESS) {
                                    apiResponse = response.body().toString()
                                } else {
                                    showToast(context, alertMessage)
                                    apiResponse = ""
                                }
                            } else {
                                if (alertCode == ApiCode.INVALID_TOKEN) {
                                    showToast(context, alertMessage)
                                    //TODO: i can doing something like logout
                                } else {
                                    showToast(context, alertMessage)
                                }
                                apiResponse = ""
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()

                            showToast(context, e.message)
                            apiResponse = ""
                        }

                    } else {

                        if (response.errorBody() != null) {
                            try {
                                if (!TextUtils.isEmpty(response.errorBody()?.string())) {
                                    alertMessage = response.errorBody()?.string()
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                                alertMessage = "Not found error message"
                            }

                        }

                        showToast(context, alertMessage)
                        apiResponse = ""
                    }
                }

            }

            callback.onFinishRequest(apiResponse)

        }

        override fun onFailure(call: Call<T>, t: Throwable) {

            if (t is IOException) {
                alertMessage = "No internet connection"
            } else {
                alertMessage = t.message
            }

            showToast(context, alertMessage)
            apiResponse = ""

            callback.onFinishRequest(apiResponse)

        }
    })
}

private fun showToast(context: Context, alertMessage: String?) {
    Toast.makeText(context, alertMessage, Toast.LENGTH_SHORT).show()
}

private fun getJson(stringJson: String, modelClass: Class<*>): Any? {
    try {
        val gsonBuilder = GsonBuilder()
        val gson: Gson = gsonBuilder.create()
        val obj: JsonObject = JsonParser().parse(stringJson).asJsonObject
        return gson.fromJson(obj, modelClass)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        return null
    }
}