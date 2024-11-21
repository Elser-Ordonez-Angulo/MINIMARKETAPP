package com.cibertec.minimarketapp

import android.app.Application

class MinimarketApp: Application() {

    companion object {
        var prefs: MinimarketPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = MinimarketPreferences(applicationContext)
    }

}