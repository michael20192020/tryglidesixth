/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

@Run
class SwordTest {
    @TestCase(id = "1")
    fun showCase(testId : String){
        println("Run SwordTest ID = ${testId}")
    }
}

