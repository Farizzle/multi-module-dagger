package com.example.core

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.example.api.LocationService
import javax.inject.Inject

class LocationServicesImpl @Inject constructor(private val context: Context): LocationService {

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

    override fun startLocationUpdates() {
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
    }

    override fun stopLocationUpdates() {
        locationManager?.removeUpdates(locationListener!!)
    }
}