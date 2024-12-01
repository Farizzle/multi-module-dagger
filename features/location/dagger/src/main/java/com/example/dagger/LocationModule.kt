package com.example.dagger

import android.content.Context
import com.example.api.LocationService
import com.example.core.LocationServicesImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Provides
    @Singleton
    fun provideLocationService(context: Context): LocationService {
        return LocationServicesImpl(context)
    }

}