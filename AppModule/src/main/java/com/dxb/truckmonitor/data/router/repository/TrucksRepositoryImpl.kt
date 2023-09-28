package com.dxb.truckmonitor.data.router.repository

import com.dxb.truckmonitor.data.router.factory.DataFactory
import com.dxb.truckmonitor.data.router.factory.Source
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.domain.router.repository.TrucksRepository
import javax.inject.Inject

class TrucksRepositoryImpl @Inject constructor(dataFactory: DataFactory) : TrucksRepository {

    private val networkRepo = dataFactory.create(Source.NETWORK)
    private val localRepo = dataFactory.create(Source.LOCAL)

    override suspend fun getDataFromLocal(): ArrayList<TruckModel> {
        return localRepo.getData()
    }

    override suspend fun getDataFromNetwork(): ArrayList<TruckModel> {
        return networkRepo.getData()
    }

    override suspend fun saveData(truckModelList: ArrayList<TruckModel>) {
        localRepo.saveData(truckModelList)
    }

    override suspend fun purgeData() {
        localRepo.purgeAllData()
    }
}