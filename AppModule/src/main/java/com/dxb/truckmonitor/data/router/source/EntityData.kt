package com.dxb.truckmonitor.data.router.source

import com.dxb.truckmonitor.data.dto.model.TruckModel

interface EntityData {

    suspend fun getData(): ArrayList<TruckModel>

    suspend fun saveData(truckModelList: List<TruckModel>) {

    }

    suspend fun purgeAllData() {

    }
}