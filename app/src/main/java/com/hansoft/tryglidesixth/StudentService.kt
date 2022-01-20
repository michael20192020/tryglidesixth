/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

interface StudentService {
    var name : String
    var sex : String
    var age : Int
    fun save(student: Student3)
    fun print(){
        println("I am student")
    }
}