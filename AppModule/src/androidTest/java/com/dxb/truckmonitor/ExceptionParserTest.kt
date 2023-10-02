package com.dxb.truckmonitor

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dxb.truckmonitor.utils.ExceptionParser
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.net.UnknownHostException

@RunWith(AndroidJUnit4::class)
class ExceptionParserTest {

    @Test
    fun SimulateExceptions() {

        Assert.assertEquals(R.string.server_connection_error, ExceptionParser.getMessage(UnknownHostException()))
        Assert.assertEquals(R.string.error_general, ExceptionParser.getMessage(ArithmeticException()))
    }
}