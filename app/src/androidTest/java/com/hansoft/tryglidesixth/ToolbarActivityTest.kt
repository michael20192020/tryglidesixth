package com.hansoft.tryglidesixth


import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
@RunWith(AndroidJUnit4::class)
class ToolbarActivityTest {
    @get:Rule
    public var rule: ActivityTestRule<ToolbarActivity> = ActivityTestRule(ToolbarActivity::class.java)
    @Test
    @Throws(Exception::class)
    fun toolbarTest() {

        val title: CharSequence = rule.activity.resources.getString(R.string.hello_espresso)

        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(title)))
    }

    companion object {
        private fun withToolbarTitle(title: CharSequence): BoundedMatcher<Any?, Toolbar?> {
            return object : BoundedMatcher<Any?, Toolbar?>(Toolbar::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("with toolbar title :")
                }

                override fun matchesSafely(item: Toolbar?): Boolean {
                    return item!!.getTitle().equals(title)
                }


            }
        }
    }
}