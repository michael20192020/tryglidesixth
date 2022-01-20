/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class Triangle(override var width: Double, override var height: Double, override var radius: Double) : Shape() {
    override fun area(): Double {
        return 0.5 * width * height
    }

    override fun onClick() {
        println("${this::class.simpleName} is clicked")
    }
}