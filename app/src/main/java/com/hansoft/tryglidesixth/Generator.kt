/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

interface Generator<T> {
    operator fun next() : T
    fun <T> console(t:T)
}