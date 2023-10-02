package com.dxb.truckmonitor

import android.system.ErrnoException
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dxb.truckmonitor.utils.ExceptionParser
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@RunWith(AndroidJUnit4::class)
class ExceptionParserTest {

    @Test
    fun simulateExceptions() {

        Assert.assertEquals(R.string.server_connection_error, ExceptionParser.getMessage(SocketTimeoutException()))
        Assert.assertEquals(R.string.server_connection_error, ExceptionParser.getMessage(UnknownHostException()))
        Assert.assertEquals(R.string.server_connection_error, ExceptionParser.getMessage(ConnectException()))
        Assert.assertEquals(R.string.server_connection_error, ExceptionParser.getMessage(SocketException()))
        Assert.assertEquals(R.string.server_connection_error, ExceptionParser.getMessage(ErrnoException("Test", 101)))
        Assert.assertEquals(R.string.server_connection_error, ExceptionParser.getMessage(Exception("android_getaddrinfo failed")))
        Assert.assertEquals(R.string.error_general, ExceptionParser.getMessage(ArithmeticException()))
    }
}