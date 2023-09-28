package com.dxb.truckmonitor.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dxb.truckmonitor.data.database.dao.TruckDao
import com.dxb.truckmonitor.data.database.model.TruckEntity

@Database(
    version = 1,
    entities = [TruckEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun truckDao(): TruckDao
}