/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class Student3 {
    lateinit var name : String
    var age : Int = 0
    lateinit var sex : String

    constructor()
    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    constructor(name: String, age: Int, sex: String) {
        this.name = name
        this.age = age
        this.sex = sex
    }

    override fun toString(): String {
        return "Student3(name='$name', age=$age, sex='$sex')"
    }

    companion object {
        fun printscore(score : Int)
        {
            println("my score is $score")
        }

    }


}