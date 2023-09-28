package com.dxb.truckmonitor.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trucks")
data class TruckEntity(
    @PrimaryKey
    val plateNo: String,
    val driverName: String?,
    val lat: Double,
    val lng: Double,
    val location: String?,
    val imageURL: String?,
    val lastUpdated: Long
)