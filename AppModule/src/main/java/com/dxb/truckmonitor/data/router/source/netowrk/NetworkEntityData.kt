package com.dxb.truckmonitor.data.router.source.netowrk

import com.dxb.truckmonitor.data.dto.model.TruckModel
import com.dxb.truckmonitor.data.router.api.TruckMonitorApi
import com.dxb.truckmonitor.data.router.source.EntityData
import javax.inject.Inject

class NetworkEntityData @Inject constructor(private val truckMonitorApi: TruckMonitorApi) :
    EntityData {

    override suspend fun getData(): ArrayList<TruckModel> {
        return truckMonitorApi.getTruckList()
    }

    override suspend fun saveData(truckModelList: List<TruckModel>) {
        //Do nothing -- nj
    }

    override suspend fun purgeAllData() {
        //Do nothing -- nj
    }
}