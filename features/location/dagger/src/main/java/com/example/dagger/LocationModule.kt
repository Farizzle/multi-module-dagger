package com.example.dagger

import android.content.Context
import com.example.api.LocationService
import com.example.api.PermissionHandler
import com.example.features.location.core.LocationServiceImpl
import com.example.features.location.core.PermissionHandlerImpl
import com.example.ui.LocationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LocationModule(private val context: Context) {
    @Provides
    fun provideLocationService(
        permissionHandler: PermissionHandler
    ): LocationService {
        return LocationServiceImpl(context, permissionHandler)
    }

    @Provides
    fun providePermissionHandler(): PermissionHandler {
        return PermissionHandlerImpl(context)
    }

    @Provides
    fun provideLocationViewModelFactory(
        locationService: LocationService,
        permissionHandler: PermissionHandler
    ): LocationViewModelFactory {
        return LocationViewModelFactory(locationService, permissionHandler)
    }

}

