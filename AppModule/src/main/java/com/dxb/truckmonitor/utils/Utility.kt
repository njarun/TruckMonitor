package com.dxb.truckmonitor.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.dxb.truckmonitor.data.session.SessionContext
import com.dxb.truckmonitor.domain.router.dto.model.TruckModel
import com.dxb.truckmonitor.presentation.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.Locale


object Utility {

    fun makeActivityFullScreen(activity: BaseActivity<*, *>) {

        try {

            val window = activity.window

            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

            activity.requestWindowFeature(Window.FEATURE_NO_TITLE)//will hide the title
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )

                window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            }
        }
        catch (ignored: Exception) { }
    }

    fun isNetworkAvailable(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

    @JvmStatic
    fun getDatePrettied(date: Long): String {
        return getDatePrettied(date, System.currentTimeMillis())
    }

    @JvmStatic
    fun getDatePrettied(date: Long, compareDate: Long): String {

        if (date in 1 until compareDate || date == compareDate) {

            val difference = compareDate - date
            val seconds = difference / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            val days = hours / 24
            val months = days / 31
            val years = days / 365

            return if (seconds == 0L) {
                "Just now"
            }
            else if (seconds < 60) {
                if (seconds == 1L) "One second ago" else "$seconds seconds ago"
            }
            else if (seconds < 120) {
                "A minute ago"
            }
            else if (seconds < 2700) { // 45 * 60
                "$minutes minutes ago"
            }
            else if (seconds < 5400) { // 90 * 60
                "An hour ago"
            }
            else if (seconds < 86400) { // 24 * 60 * 60
                "$hours hours ago"
            }
            else if (seconds < 172800) { // 48 * 60 * 60
                "Yesterday"
            }
            else if (seconds < 2592000) { // 30 * 24 * 60 * 60
                "$days days ago"
            }
            else if (seconds < 31104000) { // 12 * 30 * 24 * 60 * 60
                if (months <= 1) "One month ago" else "$months months ago"
            }
            else {
                if (years <= 1)"One year ago" else "$years years ago"
            }
        }

        return "N. A"
    }

    @JvmStatic
    fun parseDateStringToMilliseconds(dateStr: String?): Long {

        var milliseconds = 0L

        dateStr?.let {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ENGLISH)
            val date = simpleDateFormat.parse(dateStr)
            milliseconds = date?.time ?: 0
        }

        return milliseconds
    }

    @JvmStatic
    fun sortListBasedOnTheOrder(modelList: ArrayList<TruckModel>, sortOrder: SessionContext.FEED_SORT_ORDER) {

        modelList.sortWith(
            if(sortOrder == SessionContext.FEED_SORT_ORDER.ASC)
                compareByDescending { it.lastUpdated }
            else compareBy { it.lastUpdated }
        )
    }
}