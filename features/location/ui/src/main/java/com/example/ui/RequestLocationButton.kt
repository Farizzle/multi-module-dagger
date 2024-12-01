package com.example.features.location.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ui.LocationViewModel
import com.example.ui.LocationViewModelFactory

@Composable
fun RequestLocationButton(
    context: Context,
    viewModelFactory: LocationViewModelFactory
) {
    val viewModel: LocationViewModel = viewModelFactory.create(LocationViewModel::class.java)
    val activity = context as? Activity

    val isLocationActive by viewModel.isLocationActive.collectAsState()

    val requestPermissions = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            activity?.let {
                viewModel.startLocationUpdatesIfPermitted(it)
            }
        } else {
            // maybe try re-request permission??
        }
    }

    LaunchedEffect(context) {
        requestLocationUpdatesIfNeeded(activity, viewModel, requestPermissions)
    }

    Button(onClick = {
        requestLocationUpdatesIfNeeded(activity, viewModel, requestPermissions)
    }) {
        Text(text = if (isLocationActive) "Location Active" else "Request Location")
    }
}

private fun startLocationUpdatesIfPermitted(activity: Activity?, viewModel: LocationViewModel) {
    activity?.let {
        viewModel.startLocationUpdatesIfPermitted(it)
    }
}

private fun requestLocationUpdatesIfNeeded(
    activity: Activity?,
    viewModel: LocationViewModel,
    requestPermissions: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>
) {
    if (viewModel.hasLocationPermissions()) {
        startLocationUpdatesIfPermitted(activity, viewModel)
    } else {
        requestPermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}



