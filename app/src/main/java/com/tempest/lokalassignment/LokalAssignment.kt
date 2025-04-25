package com.tempest.lokalassignment

import android.app.Application
import com.tempest.lokalassignment.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LokalAssignment: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LokalAssignment)
            modules(appModule)
        }
    }
}