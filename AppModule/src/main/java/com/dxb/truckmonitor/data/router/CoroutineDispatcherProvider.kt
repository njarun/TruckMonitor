package com.dxb.truckmonitor.data.router

import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProvider {
    fun IO() = Dispatchers.IO
}