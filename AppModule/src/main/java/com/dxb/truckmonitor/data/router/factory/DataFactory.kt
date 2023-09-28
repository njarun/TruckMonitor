package com.dxb.truckmonitor.data.router.factory

import com.dxb.truckmonitor.data.router.source.EntityData
import com.dxb.truckmonitor.data.router.source.local.LocalEntityData
import com.dxb.truckmonitor.data.router.source.netowrk.NetworkEntityData
import javax.inject.Inject

class DataFactory @Inject constructor(
    private val networkEntityData: NetworkEntityData,
    private val localEntityData: LocalEntityData
) {

    fun create(source: Source): EntityData {

        return when (source) {

            Source.NETWORK -> networkEntityData
            else -> localEntityData
        }
    }
}