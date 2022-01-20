/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class Complex {
    constructor()
    constructor(real: Int, image: Int) {
        this.real = real
        this.image = image
    }

    var real: Int = 0
    var image: Int = 0
    override fun toString(): String {
        // return "Complex(real=$real, image=$image)"
        val img = if (image >= 0) "+ ${image}i" else "- ${-image}i"
        return "$real ${img} "
    }

    operator fun plus(c: Complex): Complex {
        return Complex(this.real + c.real, this.image + c.image)
    }

    operator fun minus(c: Complex): Complex {
        return Complex(this.real - c.real, this.image - c.image)
    }

    operator fun times(c: Complex): Complex {
        return Complex(this.real * c.real - this.image * c.image , this.real * c.image + this.image * c.real)
    }

    operator fun plusAssign(c: Complex)
    {
        this.real = this.real + c.real
        this.image = this.image + c.image
    }

    operator fun minusAssign(c: Complex)
    {
        this.real = this.real - c.real
        this.image = this.image - c.image
    }

    operator fun timesAssign(c: Complex)
    {
        val real = this.real * c.real - this.image * c.image
        val image = this.real * c.image + this.image * c.real
        this.real = real
        this.image = image

    }

    operator fun divAssign(c : Complex)
    {
        val real = (this.real * c.real + this.image * c.image) / (c.real * c.real + c.image * c.image)
        val image = (this.real * c.image - this.image * c.real) / -(c.real * c.real + c.image * c.image)
        this.real = real
        this.image = image
    }
}