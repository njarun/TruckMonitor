package com.dxb.truckmonitor.domain.router.repository

import com.dxb.truckmonitor.domain.router.dto.model.TruckModel

interface TrucksRepository {

    suspend fun getDataFromLocal(): ArrayList<TruckModel>

    suspend fun getDataFromLocal(searchQuery: String): ArrayList<TruckModel>

    suspend fun getDataFromNetwork(): ArrayList<TruckModel>

    suspend fun saveData(truckModelList: ArrayList<TruckModel>)

    suspend fun purgeData()
}