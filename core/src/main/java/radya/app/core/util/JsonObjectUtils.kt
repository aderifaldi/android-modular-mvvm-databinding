package radya.app.core.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser

fun getJson(stringJson: String, modelClass: Class<*>): Any? {
    try {
        val gsonBuilder = GsonBuilder()
        val gson: Gson = gsonBuilder.create()
        val obj: JsonObject = JsonParser().parse(stringJson).asJsonObject
        return gson.fromJson(obj, modelClass)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}
