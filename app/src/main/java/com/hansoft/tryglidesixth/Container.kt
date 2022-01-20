/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class Container<K,V>(var key : K,var value : V) {
    override fun toString(): String {
        return "Container(key = $key,value = $value)"
    }

    fun <T> console(t:T)
    {
        println(t)
    }
}