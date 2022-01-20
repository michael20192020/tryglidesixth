/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

data class Student4(var id : Long,var name : String,var age : Int,var score : Int)
{
    override fun toString(): String {
        return "Student(id = $id,name = '$name',age = $age,score = '$score')"
    }
}
