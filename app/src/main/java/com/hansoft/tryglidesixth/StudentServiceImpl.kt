/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class StudentServiceImpl(override var name: String, override var sex: String, override var age: Int) : StudentService {


    override fun save(student: Student3) {
        student.name = name
        student.age = age
        student.sex = sex

    }

    override fun print() {
        println("I am student, my name is $name,i am $age years old, my sex is $sex")
    }
}