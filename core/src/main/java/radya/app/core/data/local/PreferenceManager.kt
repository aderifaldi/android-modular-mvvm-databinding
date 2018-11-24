package adr.core.data.local

import android.content.Context
import android.content.SharedPreferences
import adr.core.data.constant.Key
import adr.core.data.constant.Validation
import radya.app.core.helper.aes256.AES256

/**
 * Created by aderifaldi on 2018-01-09.
 */

fun saveString(context: Context, key: String, value: String?) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putString(key, AES256.encryptString(value)).apply()
}

fun saveInt(context: Context, key: String, value: Int) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putInt(key, value).apply()
}


fun saveLong(context: Context, key: String, value: Long) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putLong(key, value).apply()
}

fun saveBoolean(context: Context, key: String, value: Boolean?) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean(key, value!!).apply()
}

fun getString(context: Context, key: String): String {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    return AES256.decriyptString(sharedPreferences.getString(key, Validation.DEFAULT_STRING))
}

fun getInt(context: Context, key: String): Int {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    return sharedPreferences.getInt(key, Validation.DEFAULT_INT)
}

fun getLong(context: Context, key: String): Long {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    return sharedPreferences.getLong(key, 0)
}

fun getBoolean(context: Context, key: String): Boolean {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Key.PREFERENCE_NAME, Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean(key, Validation.DEFAULT_BOOLEAN)
}
