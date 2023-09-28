package com.dxb.truckmonitor.data.dto.model

import com.dxb.truckmonitor.presentation.base.adapters.BaseListItem

class TruckModel(
    val plateNo: String, //"X 19599"
    val driverName: String?, //"Wyatt Liam"
    val lat: Double, //25.357119
    val lng: Double, //55.391068
    val location: String?, //"Rolla, Sharjah, the UAE"
    val imageURL: String?, //"https://i.picsum.photos/id/583/200/300.jpg"
    val lastUpdated: String? //"2023-09-28T00:39:08+00:00"
) : BaseListItem