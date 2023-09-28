package com.dxb.truckmonitor.data.router.source.local

import com.dxb.truckmonitor.data.database.dao.TruckDao
import com.dxb.truckmonitor.data.dto.mapper.TruckEntityModelMapper.toModelList
import com.dxb.truckmonitor.data.dto.mapper.TruckModelEntityMapper.toEntityList
import com.dxb.truckmonitor.data.dto.model.TruckModel
import com.dxb.truckmonitor.data.router.source.EntityData
import javax.inject.Inject

class LocalEntityData @Inject constructor(private val truckDao: TruckDao) : EntityData {

    override suspend fun getData(): ArrayList<TruckModel> {
        return truckDao.readAll().toModelList()
    }

    override suspend fun saveData(truckModelList: List<TruckModel>) {
        truckDao.insert(truckModelList.toEntityList())
    }

    override suspend fun purgeAllData() {
        truckDao.purgeData()
    }
}