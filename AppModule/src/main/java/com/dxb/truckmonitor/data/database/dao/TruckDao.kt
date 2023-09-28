package com.dxb.truckmonitor.data.database.dao

import androidx.room.*
import com.dxb.truckmonitor.data.database.model.TruckEntity

@Dao
interface TruckDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(feed: ArrayList<TruckEntity>)

    @Query("SELECT * FROM trucks")
    fun readAll(): List<TruckEntity>

    @Query("DELETE FROM trucks")
    fun purgeData()
}