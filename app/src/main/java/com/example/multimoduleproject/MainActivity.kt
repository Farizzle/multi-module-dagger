package com.example.multimoduleproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.features.location.ui.RequestLocationButton
import com.example.multimoduleproject.core.MyApplication
import com.example.multimoduleproject.ui.theme.MultiModuleProjectTheme
import com.example.ui.LocationViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var locationViewModelFactoryProvider: Provider<LocationViewModelFactory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (application as MyApplication).appComponent
        appComponent.inject(this)


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
                        viewModelFactory = locationViewModelFactoryProvider,
                    )
                }
            }
        }
    }
}
