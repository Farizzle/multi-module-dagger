package com.example.features.location.core

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.example.api.LocationService
import com.example.api.PermissionHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationServiceImpl @Inject constructor(
    context: Context,
    private val permissionHandler: PermissionHandler
) : LocationService {

    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null

    init {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // Handle location update
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                // Handle status change
            }

            override fun onProviderEnabled(provider: String) {
                // Handle provider enabled
            }

            override fun onProviderDisabled(provider: String) {
                // Handle provider disabled
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun startLocationUpdates(activity: Activity) {
        if (permissionHandler.hasLocationPermissions()) {
            try {
                locationManager?.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0L,
                    0f,
                    locationListener!!
                )
            } catch (e: SecurityException) {
                e.printStackTrace()
                // Handle lack of permission
            }
        } else {
            permissionHandler.requestLocationPermissions(activity)
        }
    }

    override fun stopLocationUpdates() {
        locationListener?.let {
            locationManager?.removeUpdates(it)
        }
    }

    override fun handlePermissionResult(requestCode: Int, grantResults: IntArray): Boolean {
        return permissionHandler.handlePermissionResult(requestCode, grantResults)
    }
}
