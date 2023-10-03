package com.dxb.truckmonitor.presentation

import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavDeepLinkBuilder
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.dxb.truckmonitor.R
import com.dxb.truckmonitor.presentation.dashboard.DashboardActivity
import com.dxb.truckmonitor.utils.Utility
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserExperienceTest2 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun mainActivityTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        if(Utility.isNetworkAvailable(appContext))
            Assert.assertEquals(true, Utility.isNetworkAvailable(appContext))
    }

    @Test
    fun listFragmentTest() {
        launchFragment(R.id.fragment_list)
    }

    @Test
    fun mapFragmentTest() {
        launchFragment(R.id.fragment_map)
    }

    private fun launchFragment(destinationId: Int, argBundle: Bundle? = null) {
        mActivityScenarioRule.scenario.onActivity {
            it.startActivity(buildLaunchFragmentIntent(destinationId, argBundle))
        }
    }

    private fun buildLaunchFragmentIntent(destinationId: Int, argBundle: Bundle?): Intent =
        NavDeepLinkBuilder(InstrumentationRegistry.getInstrumentation().targetContext)
            .setGraph(R.navigation.navigation_app)
            .setComponentName(DashboardActivity::class.java)
            .setDestination(destinationId)
            .setArguments(argBundle)
            .createTaskStackBuilder().intents[0]
}
