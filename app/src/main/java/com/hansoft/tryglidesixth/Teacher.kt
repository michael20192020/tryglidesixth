/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

import kotlin.properties.Delegates

object Teacher {
    lateinit var name : String
    var age : Int = 0
    lateinit var sex : String
    fun hello()
    {
        println("Hello everyone, i am $name, i am $age years old,your new math teacher")
    }
}