package com.example.api

import android.app.Activity
import kotlinx.coroutines.flow.MutableStateFlow

interface LocationService {
    fun startLocationUpdates(activity: Activity)
    fun stopLocationUpdates()
    fun handlePermissionResult(requestCode: Int, grantResults: IntArray): Boolean

    val isLocationActive: MutableStateFlow<Boolean>
}