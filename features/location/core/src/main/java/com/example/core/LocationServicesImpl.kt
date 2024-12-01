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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationServiceImpl @Inject constructor(
    context: Context,
    private val permissionHandler: PermissionHandler
) : LocationService {

    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null

    private val _isLocationActive = MutableStateFlow(false)
    override val isLocationActive: MutableStateFlow<Boolean> get() = _isLocationActive

    init {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                _isLocationActive.value = true
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            }

            override fun onProviderEnabled(provider: String) {
            }

            override fun onProviderDisabled(provider: String) {
                _isLocationActive.value = false
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
                _isLocationActive.value = true
            } catch (e: SecurityException) {
                e.printStackTrace()
                _isLocationActive.value = false
            }
        } else {
            permissionHandler.requestLocationPermissions(activity)
        }
    }

    override fun stopLocationUpdates() {
        locationListener?.let {
            locationManager?.removeUpdates(it)
        }
        _isLocationActive.value = false
    }

    override fun handlePermissionResult(requestCode: Int, grantResults: IntArray): Boolean {
        return permissionHandler.handlePermissionResult(requestCode, grantResults)
    }

}
