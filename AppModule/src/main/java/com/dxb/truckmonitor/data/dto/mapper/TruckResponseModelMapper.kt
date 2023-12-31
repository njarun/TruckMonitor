package com.dxb.truckmonitor.data.dto.mapper

import com.dxb.truckmonitor.data.dto.model.TruckResponseModel
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.utils.Utility

object TruckResponseModelMapper {

    fun List<TruckResponseModel>.toModelList(): ArrayList<TruckModel> {

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
                    Utility.parseDateStringToMilliseconds(it.lastUpdated)
                )
            )
        }

        return modelList
    }
}