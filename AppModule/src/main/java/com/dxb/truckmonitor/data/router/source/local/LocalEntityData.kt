package com.dxb.truckmonitor.data.router.source.local

import com.dxb.truckmonitor.data.database.dao.TruckDao
import com.dxb.truckmonitor.data.dto.mapper.TruckEntityModelMapper.toModelList
import com.dxb.truckmonitor.data.dto.mapper.TruckModelEntityMapper.toEntityList
import com.dxb.truckmonitor.data.router.source.EntityData
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import javax.inject.Inject

class LocalEntityData @Inject constructor(private val truckDao: TruckDao,
                                          private val sessionContext: SessionContext): EntityData {

    override suspend fun getData(): ArrayList<TruckModel> {
        return truckDao.readAll().toModelList(sessionContext)
    }

    override suspend fun saveData(truckModelList: List<TruckModel>) {
        truckDao.insert(truckModelList.toEntityList())
    }

    override suspend fun purgeAllData() {
        truckDao.purgeData()
    }
}