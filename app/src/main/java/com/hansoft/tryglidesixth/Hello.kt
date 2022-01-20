/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class Hello {
    fun say() {
        println("Hello World")
    }

    fun say(msg: String) {
        println("Hello $msg")
    }


    fun people(hello: () -> Unit) {
        hello()
    }

    fun people(arg0: String, hello: (arg1: String) -> Unit) {
        hello(arg0)
    }
}