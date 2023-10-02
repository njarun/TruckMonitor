package com.dxb.truckmonitor

import com.dxb.truckmonitor.data.dto.model.TruckResponseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset


class ApiModuleTest {

    @Test
    fun testAvailability() {

        var responseValid = false

        try {

            val connection = URL("${BuildConfig.BASE_URL}/candidate").openConnection()
            val response = connection.getInputStream()
            val buffer = StringBuffer()
            BufferedReader(InputStreamReader(response, Charset.defaultCharset())).use { reader ->

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    buffer.append(line)
                }
            }

            if(buffer.isNotEmpty()) {
                val truckResponseModelList: ArrayList<TruckResponseModel> = Gson().fromJson(buffer.toString(),
                    object : TypeToken<ArrayList<TruckResponseModel>>() {}.type)
                responseValid = truckResponseModelList.isNotEmpty()
            }
        }
        catch (ignored: Exception) {

        }

        Assert.assertEquals(true, responseValid)
    }
}