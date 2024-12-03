package com.example.dagger

import com.example.ui.LocationViewModelFactory
import dagger.Component

@Component(modules = [LocationModule::class])
interface LocationComponent {
    fun locationViewModelFactory(): LocationViewModelFactory
}