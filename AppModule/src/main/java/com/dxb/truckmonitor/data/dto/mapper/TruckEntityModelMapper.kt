package com.dxb.truckmonitor.data.dto.mapper

import com.dxb.truckmonitor.data.database.model.TruckEntity
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.utils.Utility

object TruckEntityModelMapper {

    fun List<TruckEntity>.toModelList(sessionContext: SessionContext): ArrayList<TruckModel> {

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

        Utility.sortListBasedOnTheOrder(modelList, sessionContext.feedSortOrder)

        return modelList
    }
}