package com.hansoft.tryglidesixth

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.InstrumentationRegistry.getTargetContext


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by QingMei on 2017/6/23.
 * desc:屏幕旋转处理test类
 * [A04RotateActivity]
 */
@RunWith(AndroidJUnit4::class)
class RotateActivityTest {
    @get:Rule
    public var activityRule: ActivityTestRule<RotateActivity> = ActivityTestRule(RotateActivity::class.java)

    /**
     * 每次测试都会先执行setUp()中的代码，在这里我们每次都检查初始化是否TextView内容为0
     *
     * @throws Exception
     */
    @Before
    @Throws(Exception::class)
    fun setUp() {
        onView(withId(R.id.countTextView))
                .check(matches(withText("0")))
    }

    /**
     * 用户点击button，count+=1
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun buttonClickTest() {
        onView(withId(R.id.incrementButton))
                .perform(click())
        onView(withId(R.id.countTextView))
                .check(matches(withText("1")))
    }

    /**
     * 用户点击两次button，并旋转屏幕
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun click2TimesAndRotate() {
        onView(withId(R.id.incrementButton))
                .perform(click())
                .perform(click())
        onView(withId(R.id.countTextView))
                .check(matches(withText("2")))
        rotateScreen()
        onView(withId(R.id.countTextView))
                .check(matches(withText("2")))
    }

    /**
     * 不点击屏幕直接旋转屏幕
     */
    @Test
    fun noClickRotateScreen() {
        rotateScreen()
        onView(withId(R.id.countTextView))
                .check(matches(withText("0")))
    }

    /**
     * 旋转屏幕
     */
    private fun rotateScreen() {
        //获取当前屏幕的旋转方向
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val orientation = context.resources.configuration.orientation

        //旋转屏幕
        activityRule.getActivity().setRequestedOrientation(
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT else ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }
}