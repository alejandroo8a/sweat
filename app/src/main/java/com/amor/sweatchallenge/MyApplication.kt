package com.amor.sweatchallenge

import android.app.Application

class MyApplication : Application(){

    private val TAG = MyApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(ActivityModule, NetworkModule))
        }
    }

}