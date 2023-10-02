package com.dxb.truckmonitor.presentation


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dxb.truckmonitor.R
import com.dxb.truckmonitor.presentation.Commons.childAtPosition
import com.dxb.truckmonitor.presentation.splash.SplashActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun splashActivityTest() {

        onView(isRoot()).perform(Commons.waitFor(5000))

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

        onView(isRoot()).perform(Commons.waitFor(1000))

        val cardView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.feed_pager_view),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        cardView.perform(click())

        onView(isRoot()).perform(Commons.waitFor(1000))

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
    }
}
