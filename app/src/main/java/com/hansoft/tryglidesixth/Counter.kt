package com.hansoft.tryglidesixth

data class Counter(var index : Int)
operator fun Counter.plus(increment : Int) : Counter {
    return Counter(index + increment)
}