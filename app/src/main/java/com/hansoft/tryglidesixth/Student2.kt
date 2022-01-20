/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */




package com.hansoft.tryglidesixth

class Student2() {
    lateinit var name : String
    var age : Int = 0
    lateinit var sex : String

    constructor(name : String) : this()
    {
        this.name = name
    }

    constructor(name: String, age : Int) : this(name)
    {
        this.name = name
        this.age = age
    }

    constructor(name: String, age : Int, sex : String) : this(name,age)
    {
        this.name = name
        this.age = age
        this.sex = sex
    }

    override fun toString(): String {
        return "Student2(name = '$name',age = $age,sex = '$sex')"
    }

}