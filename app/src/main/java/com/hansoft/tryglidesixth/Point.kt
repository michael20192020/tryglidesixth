package com.hansoft.tryglidesixth

import java.math.BigDecimal

data class Point(val x :Int,val y : Int) {
    operator fun unaryMinus() = Point(-x, -y)
    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }
    operator fun compareTo(other:Point) : Int {
        val thisNorm = Math.sqrt((this.x * this.x + this.y * this.y).toDouble())
        val otherNorm = Math.sqrt((other.x * other.x + other.y * other.y).toDouble())
        return thisNorm.compareTo(otherNorm)
    }
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE
operator fun BigDecimal.dec() = this - BigDecimal.ONE
