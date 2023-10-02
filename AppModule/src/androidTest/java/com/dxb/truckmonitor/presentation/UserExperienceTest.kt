package com.dxb.truckmonitor.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnitRunner
import androidx.viewpager.widget.ViewPager
import com.dxb.truckmonitor.R
import com.dxb.truckmonitor.presentation.Commons.childAtPosition
import com.dxb.truckmonitor.presentation.dashboard.DashboardActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserExperienceTest: AndroidJUnitRunner() {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun splashActivityTest2() {

        onView(isRoot()).perform(Commons.waitFor(5000))

        val recyclerView = onView(
            allOf(
                withId(R.id.feed_list_view),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(isRoot()).perform(Commons.waitFor(1000))

        val materialTextView = onView(
            allOf(
                withId(R.id.title), withText("Truck Monitor"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar_tool_bar),
                        childAtPosition(
                            withId(R.id.action_bar),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        onView(isRoot()).perform(Commons.waitFor(1000))

        val appCompatImageView = onView(
            allOf(
                withId(R.id.left_icon), withContentDescription("Sort trucks"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar_tool_bar),
                        childAtPosition(
                            withId(R.id.action_bar),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        onView(isRoot()).perform(Commons.waitFor(1000))

        val searchAutoComplete = onView(
            allOf(
                withId(androidx.databinding.library.baseAdapters.R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(androidx.databinding.library.baseAdapters.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.databinding.library.baseAdapters.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("Mat"), closeSoftKeyboard())

        onView(isRoot()).perform(Commons.waitFor(1000))

        val searchAutoComplete2 = onView(
            allOf(
                withId(androidx.databinding.library.baseAdapters.R.id.search_src_text),
                withText("Mat"),
                childAtPosition(
                    allOf(
                        withId(androidx.databinding.library.baseAdapters.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.databinding.library.baseAdapters.R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(pressImeActionButton())

        onView(isRoot()).perform(Commons.waitFor(1000))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.feed_list_view),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        onView(isRoot()).perform(Commons.waitFor(1000))

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.fragment_map), withContentDescription("Map"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        onView(isRoot()).perform(Commons.waitFor(2000))

        mActivityScenarioRule.scenario.onActivity { activity ->
            val view = activity.findViewById<ViewPager>(R.id.feed_pager_view)
            val itemCount = view.adapter?.count ?: 0
            if(itemCount > 0)
                view.setCurrentItem(itemCount.minus(1), true)
        }

        onView(isRoot()).perform(Commons.waitFor(2000))

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.left_icon), withContentDescription("Sort trucks"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar_tool_bar),
                        childAtPosition(
                            withId(R.id.action_bar),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView2.perform(click())

        onView(isRoot()).perform(Commons.waitFor(3000))

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.fragment_list), withContentDescription("List"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        onView(isRoot()).perform(Commons.waitFor(1000))

        val appCompatImageView3 = onView(
            allOf(
                withId(androidx.databinding.library.baseAdapters.R.id.search_close_btn),
                withContentDescription("Clear query"),
                childAtPosition(
                    allOf(
                        withId(androidx.databinding.library.baseAdapters.R.id.search_plate),
                        childAtPosition(
                            withId(androidx.databinding.library.baseAdapters.R.id.search_edit_frame),
                            1
                        )
                    ), 1
                ),
                isDisplayed()
            )
        )
        appCompatImageView3.perform(click())

        onView(isRoot()).perform(Commons.waitFor(1000))

        Commons.hideKeyboard()

        onView(isRoot()).perform(Commons.waitFor(1000))

        val bottomNavigationItemView3 = onView(
            allOf(
                withId(R.id.fragment_map), withContentDescription("Map"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView3.perform(click())

        onView(isRoot()).perform(Commons.waitFor(1000))

        val bottomNavigationItemView4 = onView(
            allOf(
                withId(R.id.fragment_list), withContentDescription("List"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView4.perform(click())

        onView(isRoot()).perform(Commons.waitFor(1000))

        mActivityScenarioRule.scenario.onActivity { activity ->
            val view = activity.findViewById<RecyclerView>(R.id.feed_list_view)
            val itemCount = view.adapter?.itemCount ?: 0
            if(itemCount > 0)
                view.scrollToPosition(itemCount.minus(1))
        }

        onView(isRoot()).perform(Commons.waitFor(1000))
    }
}
