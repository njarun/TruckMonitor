package com.dxb.truckmonitor.data.dto.mapper

import com.dxb.truckmonitor.data.database.model.TruckEntity
import com.dxb.truckmonitor.data.dto.model.TruckModel

object TruckModelEntityMapper {

    fun List<TruckModel>.toEntityList(): ArrayList<TruckEntity> {

        val entityList = ArrayList<TruckEntity>()

        forEach {

            entityList.add(
                TruckEntity(
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

        return entityList
    }
}