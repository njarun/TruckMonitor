package com.dxb.truckmonitor

import com.dxb.truckmonitor.data.database.model.TruckEntity
import com.dxb.truckmonitor.data.dto.mapper.TruckEntityModelMapper.toModelList
import com.dxb.truckmonitor.data.dto.mapper.TruckModelEntityMapper.toEntityList
import com.dxb.truckmonitor.data.dto.mapper.TruckResponseModelMapper.toModelList
import com.dxb.truckmonitor.data.dto.model.TruckResponseModel
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.utils.Utility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test

class MapperTest {

    @Test()
    fun testTruckResponseModelMapper() {

        val truckResponseModelList: ArrayList<TruckResponseModel> = Gson().fromJson(DataStore.truckResponseStr,
            object : TypeToken<ArrayList<TruckResponseModel>>() {}.type)

        val truckModelList = truckResponseModelList.toModelList(SessionContext())
        val truckModelListJson = Gson().toJson(truckModelList)

        Assert.assertEquals(true, truckModelListJson.equals(DataStore.truckModelStr))
    }

    @Test
    fun testTruckModelEntityMapper() {

        val truckModelList: ArrayList<TruckModel> = Gson().fromJson(DataStore.truckModelStr,
            object : TypeToken<ArrayList<TruckModel>>() {}.type)

        val truckEntityList = truckModelList.toEntityList()

        Assert.assertEquals(true, truckEntityList.size > 0 && truckModelList.size == truckEntityList.size)
        Assert.assertEquals(true, truckModelList[0].plateNo == truckEntityList[0].plateNo)
        Assert.assertEquals(true, truckModelList[0].driverName.equals(truckEntityList[0].driverName))
        Assert.assertEquals(true, truckModelList[0].lat.equals(truckEntityList[0].lat))
        Assert.assertEquals(true, truckModelList[0].lng.equals(truckEntityList[0].lng))
        Assert.assertEquals(true, truckModelList[0].location.equals(truckEntityList[0].location))
        Assert.assertEquals(true, truckModelList[0].imageURL.equals(truckEntityList[0].imageURL))
        Assert.assertEquals(true, truckModelList[0].lastUpdated == truckEntityList[0].lastUpdated)
    }

    @Test
    fun testTruckEntityModelMapper() {

        val truckEntityList: ArrayList<TruckEntity> = Gson().fromJson(DataStore.truckEntityStr,
            object : TypeToken<ArrayList<TruckEntity>>() {}.type)

        val truckModelList = truckEntityList.toModelList(SessionContext())

        Assert.assertEquals(true, truckEntityList.size > 0 && truckModelList.size == truckEntityList.size)
        Assert.assertEquals(true, truckModelList[0].plateNo == truckEntityList[0].plateNo)
        Assert.assertEquals(true, truckModelList[0].driverName.equals(truckEntityList[0].driverName))
        Assert.assertEquals(true, truckModelList[0].lat.equals(truckEntityList[0].lat))
        Assert.assertEquals(true, truckModelList[0].lng.equals(truckEntityList[0].lng))
        Assert.assertEquals(true, truckModelList[0].location.equals(truckEntityList[0].location))
        Assert.assertEquals(true, truckModelList[0].imageURL.equals(truckEntityList[0].imageURL))
        Assert.assertEquals(true, truckModelList[0].lastUpdated == truckEntityList[0].lastUpdated)

        Assert.assertEquals(true, true)
    }

    @Test
    fun testGetDatePrettied() {

        Assert.assertEquals(true,Utility.parseDateStringToMilliseconds("2023-10-01T19:10:11+00:00") == 1696187411000)

        val currentTime = System.currentTimeMillis()
        Assert.assertEquals(true, Utility.getDatePrettied(currentTime, currentTime) == "Just now")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime + 1000, currentTime) == "N. A")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 1), currentTime) == "One second ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 59), currentTime) == "59 seconds ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 119), currentTime) == "A minute ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 2699), currentTime) == "44 minutes ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 5399), currentTime) == "An hour ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 86399), currentTime) == "23 hours ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 172799), currentTime) == "Yesterday")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 2591999L), currentTime) == "29 days ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 31103999L), currentTime) == "11 months ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - 31536000000L, currentTime) == "One year ago")
        Assert.assertEquals(true,Utility.getDatePrettied(currentTime - 63072000000L, currentTime) == "2 years ago")
    }
}