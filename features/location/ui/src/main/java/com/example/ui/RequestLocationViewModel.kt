package com.example.ui

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.api.LocationService
import com.example.api.PermissionHandler
import javax.inject.Inject

class LocationViewModel @Inject constructor(
    private val locationService: LocationService,
    private val permissionHandler: PermissionHandler
) : ViewModel() {

    val isLocationActive = locationService.isLocationActive

    fun startLocationUpdatesIfPermitted(activity: Activity) {
        if (permissionHandler.hasLocationPermissions()) {
            locationService.startLocationUpdates(activity)
        }
    }

    fun hasLocationPermissions(): Boolean = permissionHandler.hasLocationPermissions()
}
