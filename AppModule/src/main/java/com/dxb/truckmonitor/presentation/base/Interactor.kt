package com.dxb.truckmonitor.presentation.base

import android.content.Intent
import android.os.Bundle
import android.text.Spanned
import com.dxb.truckmonitor.R
import com.dxb.truckmonitor.utils.ExceptionParser
import com.dxb.truckmonitor.utils.Utility

interface Interactor

object OnRightAction: Interactor
object OnSuccess : Interactor
class ShowToast(val message: Any): Interactor
class OpenNextScreen(val clazz: Class<*>, val bundle: Bundle? = null): Interactor
class OnException(val t: Throwable): Interactor

fun handleVMInteractions(activity: BaseActivity<*, *>, interaction: Interactor): Boolean {

    when (interaction) {

        is OpenNextScreen -> {

            val intent = Intent(activity, interaction.clazz)
            interaction.bundle?.let {
                intent.putExtras(it)
            }

            activity.startActivity(intent)
        }

        is ShowToast -> {

            when (interaction.message) {
                is Int -> activity.showToast(interaction.message)
                is String -> activity.showToast(interaction.message)
                is Spanned -> activity.showToast(interaction.message)
                else -> activity.showToast(R.string.invalid_toast_msg)
            }
        }

        is OnException -> {

            var error = ExceptionParser.getMessage(interaction.t as Exception)
            if(error == R.string.server_connection_error && !Utility.isNetworkAvailable(activity))
                error = R.string.no_internet_error

            activity.showToast(error)
        }
    }

    return true
}