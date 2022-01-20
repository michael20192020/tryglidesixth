package com.hansoft.tryglidesixth

data class Student1(var age : Int = 0)
{

    lateinit var name : String
    lateinit var sex : String
    override fun toString(): String {
        return "Student1(name = '$name',age = $age,sex = '$sex')"
    }
}

