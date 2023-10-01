package com.dxb.truckmonitor.presentation.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dxb.truckmonitor.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
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

        onView(isRoot()).perform(waitFor(3000))

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

        onView(isRoot()).perform(waitFor(1000))

        val bottomNavigationItemView2 = onView(
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
        bottomNavigationItemView2.perform(click())

        onView(isRoot()).perform(waitFor(1000))

        val bottomNavigationItemView3 = onView(
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
        bottomNavigationItemView3.perform(click())

        onView(isRoot()).perform(waitFor(1000))

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

        onView(isRoot()).perform(waitFor(1000))

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

        onView(isRoot()).perform(waitFor(1000))

        val appCompatImageView3 = onView(
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
        appCompatImageView3.perform(click())
    }

    private fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun waitFor(delay: Long): ViewAction {

        return object : ViewAction {

            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}
