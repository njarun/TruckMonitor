package com.dxb.truckmonitor.utils

import android.system.ErrnoException
import com.dxb.truckmonitor.R
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionParser {

    fun getMessage(exception: Exception, level: Int = 1): Int {
        return if(exception.cause != null && level == 1)
            getMessage(exception.cause as Exception, 2)
        else parseException(exception)
    }

    private fun parseException(exception: Exception): Int {
        return if (exception is SocketTimeoutException ||
            exception is UnknownHostException ||
            exception is ConnectException ||
            exception is SocketException ||
            exception is ErrnoException) {
            R.string.server_connection_error
        }
        else {

            exception.printStackTrace()
            generalError()
        }
    }

    private fun generalError() = R.string.error_general
}