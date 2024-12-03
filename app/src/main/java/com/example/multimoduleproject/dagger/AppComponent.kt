package com.example.multimoduleproject.dagger

import com.example.dagger.LocationModule
import com.example.multimoduleproject.MainActivity
import com.example.multimoduleproject.core.MyApplication
import com.example.ui.LocationViewModelFactory
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    LocationModule::class
])

interface AppComponent {
    fun inject(application: MyApplication)
    fun inject(activity: MainActivity)

    fun locationViewModelFactoryProvider(): Provider<LocationViewModelFactory>
}