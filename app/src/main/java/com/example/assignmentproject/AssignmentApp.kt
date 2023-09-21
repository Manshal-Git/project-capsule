package com.example.assignmentproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
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