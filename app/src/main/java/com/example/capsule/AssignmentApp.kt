package com.example.capsule

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AssignmentApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext( this@AssignmentApp )
            modules( appModule )
        }
    }
}