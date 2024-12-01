package com.example.multimoduleproject

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.api.LocationService
import com.example.features.location.ui.RequestLocationButton
import com.example.multimoduleproject.core.MyApplication
import com.example.multimoduleproject.ui.theme.MultiModuleProjectTheme
import com.example.ui.LocationViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locationService: LocationService

    @Inject
    lateinit var locationViewModelFactory: LocationViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.inject(this)
        setContent {
            MultiModuleProjectTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp)
                ) {
                    RequestLocationButton(
                        context = this@MainActivity,
                        viewModelFactory = locationViewModelFactory,
                    )
                }
            }
        }
    }
}
