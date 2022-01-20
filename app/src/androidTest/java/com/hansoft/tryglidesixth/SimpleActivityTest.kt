package com.hansoft.tryglidesixth


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class SimpleActivityTest {
    @get:Rule
    public var rule: ActivityTestRule<SimpleActivity> = ActivityTestRule(SimpleActivity::class.java)
    @Test
    fun clickTest() {
        //tvContent是否默认不显示

        onView(ViewMatchers.withId(R.id.tv_content))
            .check(matches(not(isDisplayed()))) //是否不可见

        //检查btn01的text，然后执行点击事件
        onView(withId(R.id.modifyButton))
            .check(matches(withText("Modify")))
            .perform(click())

        //检查tv内容是否修改，并且是否可见
        onView(withId(R.id.tv_content))
            .check(matches(withText("hello espresso!")))
            .check(matches(isDisplayed()))

    }

    @Test
    @Throws(Exception::class)
    fun loginTest() {


        onView(withId(R.id.nameEditText))
            .perform(clearText(), replaceText("username"), closeSoftKeyboard())
            .check(matches(withText("username")))


        //点击登录
        onView(withId(R.id.loginButton))
            .perform(click())

        //校验内容
        onView(withId(R.id.tv_content))
            .check(matches(withText("success")))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nameEditText))
            .check(matches(withText(""))) //内容是否为""
            .check(matches(withHint("input name"))) //hint内容是否为"请输入账户名"
            .check(matches(withHint(Matchers.containsString("name")))) //hint内容是否包含"账户名"

    }
}