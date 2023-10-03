package com.dxb.truckmonitor

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.dxb.truckmonitor.data.database.AppDatabase
import com.dxb.truckmonitor.data.database.dao.TruckDao
import com.dxb.truckmonitor.data.database.model.TruckEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var truckDao: TruckDao
    private lateinit var truckEntityList: ArrayList<TruckEntity>

    @Before
    fun setup() {

        truckEntityList = Gson().fromJson(DataStore.truckEntityStr, object : TypeToken<ArrayList<TruckEntity>>() {}.type)

        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        truckDao = appDatabase.truckDao()
    }

    @After
    fun teardown() {
        appDatabase.close()
    }

    @Test
    fun insertAndReadTest() = runBlockingTest {

        truckDao.insert(truckEntityList)
        var truckList = truckDao.readAll()

        var operationSuccess = false
        for (item in truckList) {
            if (item.plateNo == truckEntityList[0].plateNo) {
                operationSuccess = true
                break
            }
        }

        Assert.assertEquals(true, operationSuccess)

        truckList = truckDao.readAll("19599")

        operationSuccess = false
        for (item in truckList) {
            if (item.plateNo == truckEntityList[0].plateNo) {
                operationSuccess = true
                break
            }
        }

        Assert.assertEquals(true, operationSuccess)
    }

    @Test
    fun updateTest() = runBlockingTest {

        truckDao.insert(truckEntityList)
        val newTruck = truckEntityList[0].copy(plateNo = "Q 93974")
        val newTruckList = ArrayList<TruckEntity>()
        newTruckList.add(newTruck)
        truckDao.insert(newTruckList)
        val truckList = truckDao.readAll()

        var operationSuccess = false
        for (item in truckList) {
            if (item.plateNo == newTruck.plateNo) {
                operationSuccess = true
                break
            }
        }

        Assert.assertEquals(true, operationSuccess)
    }

    @Test
    fun deleteAllTest() = runBlockingTest {

        truckDao.insert(truckEntityList)
        truckDao.purgeData()
        val truckList = truckDao.readAll()

        var operationSuccess = true
        for (item in truckList) {
            if (item.plateNo == truckEntityList[0].plateNo) {
                operationSuccess = false
                break
            }
        }

        Assert.assertEquals(true, operationSuccess)
    }
}