package com.hansoft.tryglidesixth


import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.w3c.dom.Text

//import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher
//import com.levibostian.recyclerviewmatcher.RecyclerViewMatcher.Companion.recyclerViewWithId

/**
 * Created by QingMei on 2017/6/23.
 * desc:
 */
@RunWith(AndroidJUnit4::class)
class ListActivityTest {
    @get:Rule
    var activityTestRule: ActivityTestRule<ListActivity> = ActivityTestRule(ListActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp() {
    }

    @Test
    @Throws(Exception::class)
    fun listClickTest() {

        onView(withId(R.id.recycler_view))
                .check(matches((isDisplayed())))


        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12))


    }

    @Test
    @Throws(Exception::class)
    fun checkNullTextViewTest()
    {



        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))

         Thread.sleep(3000)

        onView(withId(R.id.recycler_view))
                .check(matches(withTextViewAtPosition(0, withText("Beavers"), R.id.item_title,"title")))
                .check(matches(withTextViewAtPosition(0, withText("Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony"), R.id.item_description,"description")))
                .check(matches(withTextViewAtPosition(1, withText("Flag"), R.id.item_title,"title")))


    }


    @Test
    @Throws(Exception::class)
    fun checkAllItemIsNullTest()
    {

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
        Thread.sleep(3000)

        for (position in 0 .. activityTestRule.activity.firstRecyclerView!!.adapter!!.itemCount) {
            onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
            onView(withId(R.id.recycler_view)).check(matches(withTextViewAtPosition(position, withId(R.id.item_title), R.id.item_title,"title")))
            onView(withId(R.id.recycler_view)).check(matches(withTextViewAtPosition(position, withId(R.id.item_description), R.id.item_description,"description")))
            onView(withId(R.id.recycler_view)).check(matches(withImageAtPosition(0, withId(R.id.item_imageHref), R.id.item_imageHref)))
        }
    }


    @Test
    @Throws(Exception::class)
    fun checkAllTitleIsNullTest()
    {

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
        Thread.sleep(3000)

        for (position in 0 .. activityTestRule.activity.firstRecyclerView!!.adapter!!.itemCount) {
            onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
            onView(withId(R.id.recycler_view)).check(matches(withTextViewAtPosition(position, withId(R.id.item_title), R.id.item_title,"title")))

        }
    }

    @Test
    @Throws(Exception::class)
    fun checkAllDescriptionIsNullTest()
    {

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
        Thread.sleep(3000)

        for (position in 0 .. activityTestRule.activity.firstRecyclerView!!.adapter!!.itemCount) {
            onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
            onView(withId(R.id.recycler_view)).check(matches(withTextViewAtPosition(position, withId(R.id.item_description), R.id.item_description,"description")))

        }
    }


    @Test
    @Throws(Exception::class)
    fun checkNullImageViewTest()
    {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))

        Thread.sleep(3000)
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.recycler_view)).check(matches(withImageAtPosition(0, withId(R.id.item_imageHref), R.id.item_imageHref)))
    }

    @Test
    @Throws(Exception::class)
    fun checkAllImageIsNullTest()
    {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))

        Thread.sleep(3000)


        for (position in 0 .. activityTestRule.activity.firstRecyclerView!!.adapter!!.itemCount) {
            onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
            onView(withId(R.id.recycler_view))
                    .check(matches(withImageAtPosition(position, withId(R.id.item_imageHref), R.id.item_imageHref)))
        }


    }


    @Throws(Exception::class)
    fun withImageAtPosition(position: Int, itemMatcher: Matcher<View?>, targetViewId: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description!!.appendText("has view id " + itemMatcher + " at position " + position);
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {



                var viewHolder : RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(position)


                if (viewHolder != null) {

                    viewHolder = viewHolder as ListViewHolder
                    val imageview = viewHolder.itemView.findViewById<ImageView>(targetViewId)





                    if (itemMatcher.matches(imageview))
                    {
                        if (imageview.getDrawable() == null)
                        {
                            Log.d("aaa", "matchesSafely: position = " + position + " imageview does not have picture")
                            return false
                        }
                        else
                        {
                            return true
                        }

                    }

                }
                return false
            }
        }
    }




    @Throws(Exception::class)
    fun withTextViewAtPosition(position: Int, itemMatcher: Matcher<View?>, targetViewId: Int, name : String): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description!!.appendText("has view id " + itemMatcher + " at position " + position);
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                var viewHolder : RecyclerView.ViewHolder? = recyclerView.findViewHolderForAdapterPosition(position)


                if (viewHolder != null) {

                    viewHolder = viewHolder as ListViewHolder
                    val targetView = viewHolder.itemView.findViewById<View>(targetViewId) as TextView


                    if (itemMatcher.matches(targetView))
                    {
                        if (targetView.text.equals("null")) {
                            Log.d("aaa", "matchesSafely: position = " + position + " does not have " + name)

                            return false
                        }
                        else
                        {
                            return true
                        }
                    }



                }
                return false
            }
        }
    }


}