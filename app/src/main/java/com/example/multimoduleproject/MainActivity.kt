package com.example.multimoduleproject

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.api.LocationService
import com.example.multimoduleproject.core.MyApplication
import com.example.multimoduleproject.ui.theme.MultiModuleProjectTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locationService: LocationService

    private val requestPermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        if (granted) {
            locationService.startLocationUpdates(this)
        } else {
            // Handle permission denial
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            MultiModuleProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Faris",
                        onStartLocationUpdates = {
                            locationService.startLocationUpdates(this@MainActivity)
                        },
                        onRequestPermissions = {
                            requestPermissions.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (locationService.handlePermissionResult(requestCode, grantResults)) {
            locationService.startLocationUpdates(this)
        } else {
            println("Permissions denied")
        }
    }
}

@Composable
fun Greeting(
    name: String,
    onStartLocationUpdates: () -> Unit,
    onRequestPermissions: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = {
            onRequestPermissions()
        }) { Text("Start location") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiModuleProjectTheme {
        Greeting(
            name = "Faris",
            onStartLocationUpdates = {},
            onRequestPermissions = {}
        )
    }
}
