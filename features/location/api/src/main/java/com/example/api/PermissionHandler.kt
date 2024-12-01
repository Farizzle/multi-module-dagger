package com.example.api

import android.app.Activity

interface PermissionHandler {
    fun hasLocationPermissions(): Boolean
    fun requestLocationPermissions(activity: Activity)
    fun handlePermissionResult(requestCode: Int, grantResults: IntArray): Boolean
}