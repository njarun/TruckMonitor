package com.dxb.truckmonitor.utils

import android.system.ErrnoException
import com.dxb.truckmonitor.R
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionParser {

    fun getMessage(exception: Exception): Int {
        return if (exception is SocketTimeoutException ||
            exception is UnknownHostException ||
            exception is ConnectException ||
            exception is SocketException ||
            exception is ErrnoException ||
            exception.message?.contains("android_getaddrinfo failed", true) == true) {
            R.string.server_connection_error
        }
        else {

            exception.printStackTrace()
            generalError()
        }
    }

    private fun generalError() = R.string.error_general
}