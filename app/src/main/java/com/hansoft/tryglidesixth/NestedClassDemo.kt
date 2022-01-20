/*
 * Copyright (c) 2021.
 * Han Technology
 *
 *
 */

package com.hansoft.tryglidesixth

class NestedClassDemo {
    class Outer{
        private val zero : Int = 0
        val one : Int = 1
        class Nested{

            fun getTwo() = 2
            class Nested1
            {
                val three = 3
                fun getFour() = 4

            }

        }

        class AnonymousInnerClassDemo{
            var isRunning = false
            fun doRun()
            {
                Thread(object : Runnable{
                    override fun run()
                    {
                        isRunning = true
                        println("doRun : i am running. isRunning = $isRunning")
                    }
                }).start()
            }

            fun doStop()
            {
                Thread {
                    isRunning = false
                    println("doStop : i am not running. isRunning = $isRunning")
                }.start()
            }

            fun doWait()
            {
                val wait = Runnable {
                    isRunning = false
                    println("doWait : i am waiting. isRunning = $isRunning")
                }
                Thread(wait).start()
            }

            fun doNotify()
            {
                val notify = {
                    isRunning = false
                    println("doNotify : i notify. isRunning = $isRunning")
                }
                Thread(notify).start()
            }



        }

        inner class Inner{
            fun accessOuter() {
                println("zero = $zero")
                println("one = $one")

            }
        }

    }
}