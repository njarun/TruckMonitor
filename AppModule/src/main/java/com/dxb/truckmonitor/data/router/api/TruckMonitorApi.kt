package com.dxb.truckmonitor.data.router.api

import com.dxb.truckmonitor.data.dto.model.TruckResponseModel
import retrofit2.http.GET

interface TruckMonitorApi {

    @GET("candidate")
    suspend fun getTruckList(): ArrayList<TruckResponseModel>
}