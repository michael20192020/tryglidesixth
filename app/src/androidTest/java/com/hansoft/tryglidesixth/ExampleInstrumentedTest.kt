package com.hansoft.tryglidesixth

//import org.hamcrest.Matchers.equalTo

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.math.log
import kotlin.reflect.KClass
import kotlin.reflect.KTypeParameter


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    public var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)


    @Mock
    var context: Context? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
    }


    fun doSomething(id:String)
    {
        println("Do something in Annotation Processing ${id} ,${Date()}")
    }

    @Test
    fun testAnno() {
        val sword = SwordTest()
        sword.showCase("10000")
        val kClass = sword::class
        val declaredFunctions = kClass.annotations
        println("declaredFunctions.count() =  " + declaredFunctions.count())
        for (f in declaredFunctions) {

              println("f.name = " + f.toString())

                if (f is Run) {
                    println("class annotation function run")
                   // val id = f.id
                  //  println("id = " + id)
                  //  doSomething(id)
                }
                else
                {
                    println("other method")
                     val id = (f as TestCase).id
                      println("id = " + id)
                      doSomething(id)
                }


        }
    }

    @Test
    fun testAnnoSecond() {
        val sword = SwordTest()
        sword.showCase("50000")
        val kClass = sword::class

        val declaredFunctions = kClass.java.declaredMethods[0].annotations
        var firstMethod = kClass.java.declaredMethods[0]

        println("declaredFunctions[0].annotations.count() =  " + declaredFunctions.count())
        for (f in declaredFunctions) {


            println("f.name = " + f.toString())

            if (f is Run) {
                println("class annotation function run")
            }
            else if (f is TestCase)
            {
                println("testcase method")
                val id = (f as TestCase).id
                println("id = " + id)
                doSomething(id)
                firstMethod.invoke(sword,id)
            }
            else
            {
                println("other method")
            }


        }

    }

    @Test
    fun testContainerSecond()
    {
        val container = ContainerSecond(mutableListOf<Int>(1, 3, 2, 5, 4, 7, 6))
        val kClass = container::class
        println("kClass.simpleName = " + kClass.simpleName)

        val container2 = ContainerSecond(mutableListOf<Int>(1, 3, 2, 5, 4,6))
        val kClass2 = container2::class
        val typeParameters = kClass2.typeParameters
        val kTypeParameter: KTypeParameter = typeParameters[0]
        println(kTypeParameter.isReified)
        println(kTypeParameter.name)
        println(kTypeParameter.upperBounds)
        println(kTypeParameter.variance)

        val constructors = kClass2.constructors
        for (KFunction in constructors) {
            KFunction.parameters.forEach {
                val name = it.name
                val type = it.type
                println("name = ${name}")
                println("type = ${type}")
                for (KTypeProjection in type.arguments){
                    println(KTypeProjection.type)
                }
            }
        }

    }


    fun isOdd(x: Int) = x % 2 != 0

    @Test
    fun testIsOdd()
    {
        println(isOdd(7))
        println(isOdd(2))
        val nums = listOf(1, 2, 3)
        val filteredNums = nums.filter(::isOdd)
        println(filteredNums)
    }


    var one = 1

    @Test
    fun testReflectProperty()
    {
        println(::one.get())
        ::one.set(2)
        println(one)

    }

    @Test
    fun testReflectFunction()
    {
        val digitRegex = "\\d+".toRegex()

        println("aaa "+ digitRegex.matches("7"))
        println("aaabbb " + digitRegex.matches("6"))
        println("aaaccc " + digitRegex.matches("5"))
        println("aaaddd " + digitRegex.matches("X"))
        val isDigit = digitRegex::matches
        println("aaaa "+ isDigit("7"))
        println("aaaabbb " + isDigit("6"))
        println("aaaaccc " + isDigit("5"))
        println("aaaaddd " + isDigit("X"))
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.hansoft.tryglidesixth", appContext.packageName)
        
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun listClickTest() {

        onView(withId(R.id.resultTextView))
                .check(matches((isDisplayed())))


        onView(withId(R.id.myRecyclerView))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(8))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))




    }


    @Test
    fun checkTitleTextView()
    {
        onView(withId(R.id.titleTextView)).check(matches(isDisplayed()))
        onView(withText("try recycler view")).check(matches(isDisplayed()));

    }

    @Test
    fun pressBack()
    {

        Espresso.pressBack()
    }


    @Test
    @Throws(InterruptedException::class)
    fun recylerviewVisibleTest() {

        onView(withId(R.id.myRecyclerView))
            .check(matches(isDisplayed()))
    }




    @Test
    fun onItemClick()
    {
        onView(allOf(withId(R.id.item_tv), withText("item 6"))).check(matches(isDisplayed())).perform(click())

    }

    fun withTitle(title: String): Matcher<RecyclerView.ViewHolder?>? {
        return object : BoundedMatcher<RecyclerView.ViewHolder?, TextViewHolder>(TextViewHolder::class.java) {
            override fun matchesSafely(item: TextViewHolder): Boolean {
                return item.mTv.getText().toString().equals(title, ignoreCase = true)
            }

            override fun describeTo(description: Description) {
                description.appendText("view holder with title: $title")
            }
        }
    }
    var matcher : Matcher<RecyclerView.ViewHolder?>? = withTitle("item 12")



    @Test
    fun onInvisibleItemClick()
    {
        Thread.sleep(2000)
        onView(withId(R.id.myRecyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(19, scrollTo()))
        onView(allOf(withId(R.id.item_tv), withText("item 17"))).check(matches(isEnabled())).perform(click())
        Thread.sleep(3000)


    }

    @Test
    fun onTitleClick()
    {
        Thread.sleep(2000)
        val items = listOf("item 11", "item 12", "item 13")
        for (item in items) {
            var matcher2 : Matcher<RecyclerView.ViewHolder?>? = withTitle(item)
            onView(withId(R.id.myRecyclerView))
                    .perform(scrollToHolder(matcher2)).check(matches(isDisplayed()))
        }
        onView(withId(R.id.myRecyclerView))
                .perform(scrollToHolder(matcher)).check(matches(isDisplayed()))
        onView(withId(R.id.myRecyclerView))
                .perform(scrollToHolder(withTitle("item 17"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.item_tv), withText("item 17"))).check(matches(isEnabled())).perform(click())

        Thread.sleep(3000)
    }

    @Test
    fun searchContentDescription()
    {

        onView(withContentDescription("addbutton")).check(matches(isDisplayed())).perform(click())

    }




    @Test
    fun addButtonClick()
    {

        onView(withId(R.id.addButton)).perform(click())

    }

    @Test
    fun deleteButtonClick()
    {

        onView(withId(R.id.deleteButton)).perform(click())

    }

    @Test
    fun checkNull()
    {
        onView(allOf(withId(R.id.item_tv), withText("null")))

    }

    ////  onView(withId(R.id.recyclerview_tag_list)).check(matches(withViewAtPosition(1, hasDescendant(allOf(withId(R.id.imageview_tag), isDisplayed())))));

    fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    @Test
    fun testJsonFile()
    {
       // try {
            val myImageList = java.util.ArrayList<MyImage>()
            val inputStream : InputStream = this@ExampleInstrumentedTest.javaClass.getClassLoader()
                .getResourceAsStream("assets/" + "a.json")
            val bufr = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            val builder = StringBuilder()
            while (bufr.readLine().also { line = it } != null) {
                builder.append(line)
                Log.d("aaa", "readjsonfile: $line")
            }

            inputStream.close()
            bufr.close()

            val obj = JSONObject(builder.toString())
            val dataArray : JSONArray = obj.getJSONArray("data")
            Log.d("aaa", "dataArray.length() : " + dataArray.length())
            for (i in 0 until dataArray.length()) {
                val lan: JSONObject = dataArray.getJSONObject(i)
                val myImage = MyImage(
                    lan.getString("title"), lan.getString("description"), lan.getString(
                        "imageHref"
                    )
                )
                myImageList.add(myImage)

                val myjasondata = " title = " + lan.getString("title") + " description = " + lan.getString(
                    "description"
                ) + " imageHref = " + lan.getString("imageHref")
                println(myjasondata)
            }


      //  }
      //  catch (e : Exception)
      //  {
      //      println("Error : " + e.toString())

      //  }
    }

}