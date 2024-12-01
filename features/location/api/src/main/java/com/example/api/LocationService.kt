package com.example.api

import android.app.Activity

interface LocationService {
    fun startLocationUpdates(activity: Activity)
    fun stopLocationUpdates()
    fun handlePermissionResult(requestCode: Int, grantResults: IntArray): Boolean
}