/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

abstract class Shape {
    abstract var width : Double
    abstract var height : Double
    abstract var radius : Double
    abstract fun area() : Double
    open fun onClick()
    {
        println("I am clicked")

    }
}