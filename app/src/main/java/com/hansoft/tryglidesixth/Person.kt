package com.hansoft.tryglidesixth

data class Person(val name : String,val age : Int)
infix fun Person.grow(year : Int) : Person
{
    return Person(name,age + year)
}
