package com.dxb.truckmonitor.data.dto.mapper

import com.dxb.truckmonitor.data.database.model.TruckEntity
import com.dxb.truckmonitor.data.dto.model.TruckModel

object TruckEntityModelMapper {

    fun List<TruckEntity>.toModelList(): ArrayList<TruckModel> {

        val modelList = ArrayList<TruckModel>()

        forEach {

            modelList.add(
                TruckModel(
                    it.plateNo,
                    it.driverName,
                    it.lat,
                    it.lng,
                    it.location,
                    it.imageURL,
                    it.lastUpdated
                )
            )
        }

        return modelList
    }
}