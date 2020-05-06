package com.amor.sweatchallenge

import android.app.Application
import androidx.multidex.MultiDex
import com.amor.sweatchallenge.di.ActivityModule
import com.amor.sweatchallenge.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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