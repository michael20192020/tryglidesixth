/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class BoxInt(var i : Int) {
    operator fun times(x:BoxInt) = BoxInt(i * x.i)
    operator fun minus(x:BoxInt) = BoxInt(i - x.i)
    operator fun div(x:BoxInt) = BoxInt(i / x.i)
    operator fun rem(x: BoxInt) = BoxInt(i % x.i)


    override fun toString(): String {
        return i.toString()
    }
}

operator fun BoxInt.plus(x:BoxInt) = BoxInt(this.i + x.i)
