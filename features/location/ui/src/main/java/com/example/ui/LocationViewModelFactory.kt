package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.api.LocationService
import com.example.api.PermissionHandler
import javax.inject.Inject

class LocationViewModelFactory @Inject constructor(
    private val locationService: LocationService,
    private val permissionHandler: PermissionHandler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationViewModel(locationService, permissionHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
