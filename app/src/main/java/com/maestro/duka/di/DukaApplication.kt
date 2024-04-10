package com.maestro.duka.di

import android.app.Application
import android.content.Context

class DukaApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        lateinit var instance: DukaApplication
            private set

        val context: Context
            get() = instance.applicationContext
    }
}