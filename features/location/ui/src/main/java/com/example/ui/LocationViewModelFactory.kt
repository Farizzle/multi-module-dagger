package com.example.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.api.LocationService
import com.example.api.PermissionHandler
import javax.inject.Inject

class LocationViewModelFactory @Inject constructor(
    private val application: Application,
    private val locationService: LocationService,
    private val permissionHandler: PermissionHandler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationViewModel(application, locationService, permissionHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
