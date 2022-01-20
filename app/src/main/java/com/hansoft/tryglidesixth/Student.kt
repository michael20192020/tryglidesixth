package com.hansoft.tryglidesixth

data class Student(var name:String,var age:Int,var sex:String)
{
    override fun toString(): String {
        return "Student(name = '$name',age = $age,sex = '$sex')"
    }
}


