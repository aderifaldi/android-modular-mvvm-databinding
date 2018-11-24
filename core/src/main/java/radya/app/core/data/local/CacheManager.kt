package adr.core.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import adr.core.data.constant.Key
import adr.core.data.constant.Validation

/**
 * Created by RadyaLabs PC on 12/12/2017.
 */

fun storeCache(context: Context, cacheType: String, data: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putString(cacheType, data).apply()
}

fun loadCache(context: Context, cacheType: String): JsonObject? {
    try {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
        return JsonParser().parse(sharedPreferences.getString(cacheType, Validation.DEFAULT_STRING)).asJsonObject
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

}
