package com.cibertec.minimarketapp

import android.content.Context
import android.content.SharedPreferences

class MinimarketPreferences(context: Context) {

    val PREF_FILENAME = "com.cibertec.minimarket"

    val pref: SharedPreferences = context.getSharedPreferences(PREF_FILENAME, 0)

    fun setBoolean(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, false)
    }

    fun setString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return pref.getString(key, "").toString()
    }

}