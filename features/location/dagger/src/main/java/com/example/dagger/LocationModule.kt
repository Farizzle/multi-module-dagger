package com.example.dagger

import android.content.Context
import com.example.api.LocationService
import com.example.api.PermissionHandler
import com.example.features.location.core.LocationServiceImpl
import com.example.features.location.core.PermissionHandlerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {
    @Provides
    @Singleton
    fun provideLocationService(
        context: Context,
        permissionHandler: PermissionHandler
    ): LocationService {
        return LocationServiceImpl(context, permissionHandler)
    }

    @Provides
    @Singleton
    fun providePermissionHandler(context: Context): PermissionHandler {
        return PermissionHandlerImpl(context)
    }
}