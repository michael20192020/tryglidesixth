package com.hansoft.tryglidesixth

import androidx.test.espresso.IdlingResource
class EspressoIdlingResource {

        private val RESOURCE = "GLOBAL"



        private val mCountingIdlingResource: SimpleCountingIdlingResource =
                SimpleCountingIdlingResource(RESOURCE)



        fun increment() {

            mCountingIdlingResource.increment()
        }



    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource? {
        return mCountingIdlingResource
    }
}