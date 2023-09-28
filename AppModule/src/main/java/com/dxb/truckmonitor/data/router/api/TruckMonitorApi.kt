package com.dxb.truckmonitor.data.router.api

import com.dxb.truckmonitor.data.dto.model.TruckModel
import retrofit2.http.GET

interface TruckMonitorApi {

    @GET("candidate")
    suspend fun getTruckList(): ArrayList<TruckModel>
}