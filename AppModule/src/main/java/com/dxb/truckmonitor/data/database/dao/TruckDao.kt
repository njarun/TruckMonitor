package com.dxb.truckmonitor.data.database.dao

import androidx.room.*
import com.dxb.truckmonitor.data.database.model.TruckEntity

@Dao
interface TruckDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(feed: ArrayList<TruckEntity>)

    @Query("""SELECT * FROM trucks ORDER BY
            CASE WHEN :sortOrder = 0 THEN lastUpdated END DESC,
            CASE WHEN :sortOrder = 1 THEN lastUpdated END ASC""")
    fun readAll(sortOrder: Int = 0): List<TruckEntity>

    @Query("""SELECT * FROM trucks where plateNo COLLATE NOCASE LIKE '%' || :searchQuery || '%'
            OR driverName COLLATE NOCASE LIKE '%' || :searchQuery || '%'
            OR location COLLATE NOCASE LIKE '%' || :searchQuery || '%' ORDER BY
                CASE WHEN :sortOrder = 0 THEN lastUpdated END DESC,
                CASE WHEN :sortOrder = 1 THEN lastUpdated END ASC""")
    fun readAll(searchQuery: String, sortOrder: Int = 0): List<TruckEntity>

    @Query("DELETE FROM trucks")
    fun purgeData()
}