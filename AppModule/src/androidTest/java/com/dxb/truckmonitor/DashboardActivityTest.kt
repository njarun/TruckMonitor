package com.dxb.truckmonitor

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dxb.truckmonitor.presentation.dashboard.DashboardActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DashboardActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun mainActivityTest() {

    }
}