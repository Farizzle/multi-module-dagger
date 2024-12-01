package com.example.multimoduleproject.core

import android.app.Application
import com.example.multimoduleproject.dagger.AppComponent
import com.example.multimoduleproject.dagger.AppModule
import com.example.multimoduleproject.dagger.DaggerAppComponent

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }
}