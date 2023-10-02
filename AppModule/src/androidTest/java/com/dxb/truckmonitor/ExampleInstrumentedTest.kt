package com.dxb.truckmonitor

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.dxb.truckmonitor.utils.Utility
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.dxb.truckmonitor", appContext.packageName)
    }

    @Test
    fun testGetDatePrettied() {

        assertEquals(true, Utility.parseDateStringToMilliseconds("2023-10-01T19:10:11+00:00") == 1696187411000)

        val currentTime = System.currentTimeMillis()
        assertEquals(true, Utility.getDatePrettied(currentTime, currentTime) == "Just now")
        assertEquals(true, Utility.getDatePrettied(currentTime + 1000, currentTime) == "N. A")
        assertEquals(true, Utility.getDatePrettied(currentTime - (1000 * 1), currentTime) == "One second ago")
        assertEquals(true, Utility.getDatePrettied(currentTime - (1000 * 59), currentTime) == "59 seconds ago")
        assertEquals(true, Utility.getDatePrettied(currentTime - (1000 * 119), currentTime) == "A minute ago")
        assertEquals(true, Utility.getDatePrettied(currentTime - (1000 * 2699), currentTime) == "44 minutes ago")
        assertEquals(true, Utility.getDatePrettied(currentTime - (1000 * 5399), currentTime) == "An hour ago")
        assertEquals(true, Utility.getDatePrettied(currentTime - (1000 * 86399), currentTime) == "23 hours ago")
        assertEquals(true, Utility.getDatePrettied(currentTime - (1000 * 172799), currentTime) == "Yesterday")
        assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 2591999L), currentTime) == "29 days ago")
        assertEquals(true,Utility.getDatePrettied(currentTime - (1000 * 31103999L), currentTime) == "11 months ago")
        assertEquals(true, Utility.getDatePrettied(currentTime - 31536000000L, currentTime) == "One year ago")
        assertEquals(true, Utility.getDatePrettied(currentTime - 63072000000L, currentTime) == "2 years ago")
    }
}