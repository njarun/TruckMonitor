package com.dxb.truckmonitor.data.router.source.netowrk

import com.dxb.truckmonitor.data.dto.mapper.TruckResponseModelMapper.toModelList
import com.dxb.truckmonitor.data.router.api.TruckMonitorApi
import com.dxb.truckmonitor.data.router.source.EntityData
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import javax.inject.Inject

class NetworkEntityData @Inject constructor(private val truckMonitorApi: TruckMonitorApi,
                                            private val sessionContext: SessionContext): EntityData {

    override suspend fun getData(): ArrayList<TruckModel> {
        return truckMonitorApi.getTruckList().toModelList(sessionContext)
    }
}