/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class ContainerSecond<T : Comparable<T>> : BaseContainer<Int> {
    var elements : MutableList<T>
    constructor(elements: MutableList<T>) : super() {
        this.elements = elements
    }

    fun sort() : ContainerSecond<T> {
        elements.sort()
        return this
    }

    override fun toString(): String {
        return "ContainerSecond(elements=$elements)"
    }


}