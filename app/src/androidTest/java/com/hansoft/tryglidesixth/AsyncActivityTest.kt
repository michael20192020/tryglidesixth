package com.hansoft.tryglidesixth


import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by QingMei on 2017/6/22.
 * desc:
 */
@RunWith(AndroidJUnit4::class)
class AsyncActivityTest {
    @get:Rule
    public var activityRule: ActivityTestRule<AsyncActivity> = ActivityTestRule(AsyncActivity::class.java)
    @Before
    @Throws(Exception::class)
    fun setUp() {
        IdlingRegistry.getInstance().register(activityRule.getActivity().countingIdlingResource)
    }

    @Test
    @Throws(Exception::class)
    fun loadImage() {
        onView(withId(R.id.loadPictureButton))
                .check(matches(withText("get image")))


        onView(withId(R.id.loadPictureButton))
                .perform(click())

        onView(withId(R.id.loadPictureButton))
                .check(matches(withText("success!")))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(activityRule.getActivity().countingIdlingResource)
    }
}