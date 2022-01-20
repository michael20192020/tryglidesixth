package com.hansoft.tryglidesixth

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import java.io.*
import java.math.BigDecimal
import java.net.URL
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

    }


    fun getArrayList(): List<String> {
        val arrayList = ArrayList<String>()
        arrayList.add("A")
        arrayList.add("B")
        arrayList.add("C")
        return arrayList
    }

    fun doSomething(id: String)
    {
        println("Do something in Annotation Processing ${id} ,${Date()}")
    }


    @Test
    fun testAnnoSecond() {
        val sword = SwordTest()
        sword.showCase("50000")
        val kClass = sword::class

        val declaredFunctions = kClass.java.declaredMethods[0].annotations
        var firstMethod = kClass.java.declaredMethods[0]

        println("declaredFunctions[0].annotations.count() =  " + declaredFunctions.count())
        for (f in declaredFunctions) {


            println("f.name = " + f.toString())

            if (f is Run) {
                println("class annotation function run")
            }
            else if (f is TestCase)
            {
                println("testcase method")
                val id = (f as TestCase).id
                println("id = " + id)
                //doSomething(id)
                println("Do something in Annotation Processing ${id} ,${Date()}")
                firstMethod.invoke(sword, id)
            }
            else
            {
                println("other method")
            }


        }

    }




    @Test
    fun testArrayList()
    {
        val list = getArrayList()
        Assert.assertTrue(list.size == 3)
        for((index, value) in list.withIndex())
        {
            println("the element at $index is $value")
        }
        (1..10).forEach { println(it) }
        println("sumFact(10) = " + sumFact(10))
    }

    fun sumFact(n: Int) : Int
    {
        var sum = 0
        for(i in 1..n)
        {
            sum = sum + fact(i)
        }
        return sum
    }


    @Test
    fun testLoginUser()
    {
        val loginUser = LoginUser("tom", "123")
        val (username, password) = loginUser
        println("username = ${username}, password = ${password}")
    }

    @Test
    fun testMap()
    {
        val map = mapOf(Pair(1, "A"), Pair("2", "B"))
        for (entry in map) {
            println("${entry.key},${entry.value}")
            println("${entry.key.javaClass},${entry.value.javaClass}")
        }
        val typeMap = mapOf<Int, String>(Pair(3, "c"), Pair(4, "d"))

        for (entry in typeMap) {
            println("${entry.key},${entry.value}")
            println("${entry.key.javaClass},${entry.value.javaClass}")
        }

        val newMap = typeMap.toMutableMap()
        for (entry in newMap) {
            println("${entry.key},${entry.value}")
            println("${entry.key.javaClass},${entry.value.javaClass}")
        }
        val mutableMap1 = mutableMapOf<String, String>()
        mutableMap1.put("name", "tom")
        mutableMap1.put("age", "30")
        mutableMap1.put("sex", "male")
        for (entry in mutableMap1) {
            println("${entry.key},${entry.value}")
        }
        assertTrue(mutableMap1.containsKey("age"))
        var mutableMap2 = mutableMapOf<Int, String>(1 to "a", 2 to "b", 3 to "c")
        mutableMap2.put(4, "d")
        mutableMap2.put(5, "e")
        println(mutableMap2)
    }

    @Test
    fun testEnum()
    {
        val north = Direction.NORTH
        println(north.name)
        println(north.ordinal)
        println(north.declaringClass.name)
        assertTrue(north is Direction)

        val color = Color.GREEN
        println(color.name)
        println(color.ordinal)
        println(color.declaringClass.name)
    }

    @Test
    fun testNestedClass()
    {
        val one = NestedClassDemo.Outer().one
        val two = NestedClassDemo.Outer.Nested().getTwo()
        val three = NestedClassDemo.Outer.Nested.Nested1().three
        val four = NestedClassDemo.Outer.Nested.Nested1().getFour()
        println(one)
        println(two)
        println(three)
        println(four)
        NestedClassDemo.Outer().Inner().accessOuter()
        NestedClassDemo.Outer.AnonymousInnerClassDemo().doRun()
        NestedClassDemo.Outer.AnonymousInnerClassDemo().doWait()
        NestedClassDemo.Outer.AnonymousInnerClassDemo().doStop()
        NestedClassDemo.Outer.AnonymousInnerClassDemo().doNotify()

    }

    @Test
    fun testLambdaFunction()
    {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        println(list.filter { it % 2 == 1 })

        val sum = fun(x: Int, y: Int) : Int {return x + y}
        println(sum(2, 3))

        val strlist = listOf("a", "ab", "abc", "abcd", "abcde", "bcd", "efg")
        println(strlist.filter { it.length % 2 == 1 })
        val f = fun(x: Int) = x % 2 == 1
        val g = fun(s: String) = s.length
        val h = fun(g: (String) -> Int, f: (Int) -> Boolean) : (String) -> Boolean {return  {f(g(it))}}

        println(strlist.filter(h(g, f)))

    }

    fun myfun() : String {
        println("execute myfun function")
        return "this is return value of myfun function"
    }

    @Test
    fun testRunFun()
    {
        myfun()
        run({ myfun() })
        run{ myfun() }
        run { println("abc") }
    }

    @Test
    fun testApplyFun()
    {
        val list = mutableListOf<String>()
        list.add("a")
        list.add("b")
        list.add("c")
        println("normal writing list = $list")
        println(list)

        val a = ArrayList<String>().apply {
            add("a")
            add("b")
            add("c")
            println("apply function writing, this = $this")
        }
        println(a)
        a.let { println(it) }

    }

    @Test
    fun testLetFun()
    {
        1.let { println(it)}
        "abc".let { println(it)}
        myfun().let { println(it)}
    }

    @Test
    fun testAlsoFun()
    {
        val a = "abc".also { println(it) }
        println(a)
        a.let {
            println(it)
        }
    }

    fun String.stream() = FileInputStream(this)
    fun FileInputStream.buffered() = BufferedInputStream(this)
    fun InputStream.reader(charset: String) = InputStreamReader(this, charset)
    fun Reader.readLines(): List<String> {
        val result = arrayListOf<String>()
        forEachLine {
            result.add(it)
        }
        return result
    }

    @Test
    fun testStreamDSL()
    {
        val lines =
                "src/main/res/raw/c.txt"
                        .stream()
                        .buffered()
                        .reader("utf-8")
                        .readLines()
        lines.forEach(::println)
    }

    fun <E> List<E>.select(): List<E> = this

    fun <E> List<E>.where(predicate: (E) -> Boolean): List<E> {
        val list = this
        val result = arrayListOf<E>()
        for (e in list) {
            if (predicate(e)) {
                result.add(e)
            }
        }
        return result
    }

    fun <E> List<E>.and(predicate: (E) -> Boolean): List<E> {
        return where(predicate)
    }

    @Test
    fun testSqlDsl()
    {
        val students = listOf(
            Student5("jack", "M", 90),
            Student5("alice", "F", 70),
            Student5("bob", "M", 60),
            Student5("bill", "M", 80),
            Student5("helen", "F", 100)
        )
        val queryResult = students.select().where { it.score >= 80 }.and { it.sex == "M" }
        println(queryResult)
    }

    @Test
    fun testBoxInt()
    {
        val a = BoxInt(3)
        val b = BoxInt(7)
        println(a + b)
        println(b - a)
        println(a * b)
        println(b / a)
        println(b % a)

    }

    @Test
    fun testComplex()
    {
        val c1 = Complex(1, 1)
        val c2 = Complex(2, 2)
        val p = c1 + c2
        val m = c1 - c2
        val t = c1 * c2
        println(p)
        println(m)
        println(t)
        var c3 = Complex(3, 3)
        c3.plusAssign(c1)
        println(c3)
        var c4 = Complex(4, 4)
        c4.minusAssign(c2)
        println(c4)
        var c5 = Complex(1, 1)
        c5.timesAssign(c1)
        println(c5)
        var c6 = Complex(0, 2)
        c6.divAssign(c1)
        println(c6)


    }


    @Test
    fun testAnno() {
      //  val sword = SwordTest()
      //  sword.testCase("10000")
    }



    @Test
    fun testWithFun()
    {
        val list = mutableListOf<String>()
        list.add("a")
        list.add("b")
        list.add("c")
        println("normal writing list = $list")
        println(list)

        with(ArrayList<String>()) {
            add("a")
            add("b")
            add("c")
            println("with function writing, this = $this")
        }.let{
            println(it)
        }

    }


    @Test
    fun testPairTriple()
    {
        val (a, b) = Pair(4, "5")
        val (i, j, k) = Triple(1, "a", 2.0)
        println("$j,$i,$k,$a,$b")
        println(i.javaClass.name + "," + j.javaClass.name + "," + k.javaClass.name)
        var p1 = Pair(1, "a")
        var p2 = Pair(1, "a")
        assertTrue(p1.equals(p2))
        println("p1.first = " + p1.first + " p1.second = " + p1.second)
        println("class of p1.first = " + p1.first.javaClass + ", class of p1.second = " + p1.second.javaClass)
    }

    @Test
    fun testWhile()
    {
        var x = 10
        while (x > 0)
        {
            x --
            println(x)
        }

        var y = 10
        do
        {
            y ++
            println(y)
        }while (y < 20)
    }

    @Test
    fun testBreak()
    {
        for (i in 1..10)
        {
            println(i)
            if (i % 2 == 0) {
                break
            }
        }
    }

    @Test
    fun testContinue()
    {
        for (i in 1..10)
        {

            if (i % 2 == 0) {
                continue
            }
            println(i)
        }
    }


    @Test
    fun testSumAndMax()
    {
        println(sum(1, 10))
        println(max(3, 7))
        val sum2 = fun(a: Int, b: Int) = a + b
        println(sum2(6, 8))
        val sum3 = fun(a: Int, b: Int) = {a + b}
        println(sum3(6, 8).invoke())
        println(sum3(6, 8)())
    }

    @Test
    fun testLambda()
    {
        val intArray = intArrayOf(1, 2, 3, 4, 5)
        intArray.forEach {
            if (it == 3)
                return
            println(it)
        }

    }


    @Test
    fun testLambda2()
    {
        val intArray = intArrayOf(1, 2, 3, 4, 5)
        intArray.forEach(
            fun(a: Int) {
                if (a == 3)
                    return
                println(a)
            }
        )
    }

    @Test
    fun testLabel()
    {
        val intArray = intArrayOf(1, 2, 3, 4, 5)
        intArray.forEach here@ {
            if (it == 3)
                return@here
            println(it)
        }
        intArray.forEach  {
            if (it == 3)
                return@forEach
            println(it)
        }
    }

    fun sum(a: Int, b: Int) = a + b
    fun max(a: Int, b: Int) = if (a>b) a else b

    @Test
    fun testThrowException()
    {
        fail("hello")
    }

    @Test
    fun testThrowException2()
    {
        val ex : Nothing = throw Exception("error")

        ex
    }


    fun fail(message: String) : Nothing {

        throw IllegalArgumentException(message)
    }

    @Test
    fun testCounterIndexPlus()
    {
        val c = Counter(1)
        val cplus = c + 10
        println(cplus)
        println("a" in "abc")
        println("d" !in "abc")
        var a = 3
        var b = 4
        a += b
        println(a)
        a -= b
        println(a)
        a *= b
        println(a)
        a /= b
        println(a)
        a %= b
        println(a)


    }

    @Test
    fun testInfixFunction()
    {
        val person = Person("jack", 20)
        println(person.grow(2))
        println(person grow 2)
    }

    @Test
    fun testElvis()
    {
        var a : String? = "tom"
        var b = a?:"unknown"
        println(b)
        a = null
        b = a?:"unknown"
        println(b)

    }

    @Test
    fun testPointUnaryMinus()
    {
        val p = Point(1, 1)
        val np = -p
        println(np)
        println(-p)
        var a = 5
        println(a++)
        println(++a)

        val p1 = Point(1, 1)
        val p2 = Point(1, 1)
        val p3 = Point(1, 3)
        println(p1 >= p2)
        println(p3 > p1)
    }



    @Test
    fun testBigDecimal()
    {
        var bigDecimal1 = BigDecimal(100)
        var bigDecimal2 = BigDecimal(100)
        val tmp1 = bigDecimal1++
        val tmp2 = ++bigDecimal2
        println(tmp1)
        println(tmp2)
        println(bigDecimal1)
        println(bigDecimal2)

        var bigDecimal3 = BigDecimal(100)
        var bigDecimal4 = BigDecimal(100)
        val tmp3 = bigDecimal3--
        val tmp4 = --bigDecimal4
        println(tmp3)
        println(tmp4)
        println(bigDecimal3)
        println(bigDecimal4)

        val bd1 = BigDecimal.ONE
        val bd2 = BigDecimal.ONE
        val bd3 = bd1 < bd2
        val bd4 = bd1 == bd2
        val bd5 = bd1 === bd2
        println(bd3)
        println(bd4)
        println(bd5)
    }

    fun String.firstChar() : String
    {
        if (this.length == 0)
        {
            return ""
        }
        return this[0].toString()
    }

    @Test
    fun testFirstChar()
    {
        println("hello".firstChar())
        Assert.assertTrue("hello".firstChar() == "h")
    }

    fun String.lastChar() : String
    {
        if (this.length == 0)
        {
            return ""
        }
        return this[this.length - 1].toString()
    }

    @Test
    fun testLastChar()
    {
        println("hello".lastChar())
        Assert.assertTrue("hello".lastChar() == "o")
    }

    @Test
    fun testInt()
    {
        val a : Int = 1000
        val b : Int = 1000
        Assert.assertTrue(a === b)
        Assert.assertTrue(a == b)
        val c : Int? = 1000
        val d : Int? = 1000
        Assert.assertTrue(c == d)
        Assert.assertTrue(c !== d)
    }

    @Test
    fun testArray()
    {
        val square = Array(5, { i -> i * i })
        square.forEach(::println)
    }

    fun unitExample()
    {
        println("Hello, unit")
    }

    fun unitReturn1()
    {

    }

    fun unitReturn2()
    {
        return Unit
    }

    fun unitReturn3() : Unit
    {

    }

    @Test
    fun testUnit()
    {
        val helloUnit = unitExample()
        println(helloUnit)
        val unit1 = unitReturn1()
        println(unit1)
        val unit2 = unitReturn2()
        println(unit2)
        val unit3 = unitReturn3()
        println(unit3)
    }

    fun strlen(ani: Any) : Int
    {
        if (ani is String)
        {
            return ani.length
        }
        else if (ani is Number)
        {
            return ani.toString().length
        }
        else if (ani is Char)
        {
            return 1
        }
        else if (ani is Boolean)
        {
            return 1
        }
        println("Not A String")
        return -1
    }

    @Test
    fun testStudent()
    {
        val student = Student("tom", 20, "male")
        println("student = $student")
    }

    @Test
    fun testStudent1()
    {
        val student1 = Student1(30)
        student1.name = "Mary"
        student1.sex = "female"
        println("student1 = $student1")
    }

    @Test
    fun testStudent3()
    {
        var student3 = StudentServiceImpl("tom", "male", 20)
        student3.print()
        student3.name = "Mary"
        student3.sex = "female"
        student3.age = 25
        student3.print()
        Student3.printscore(50)
    }


    @Test
    fun testHello()
    {
        val h = Hello()
        h.people(h::say)
        h.people("Android", h::say)
        h.people("Kotlin", h::say)
    }

    @Test
    fun testObject()
    {
        Teacher.name = "Michael"
        Teacher.age = 30
        Teacher.sex = "man"
        Teacher.hello()


    }


    @Test
    fun testAbstractClass()
    {
        var r = Rectangle(3.0, 4.0, 0.0)
        println("The area of rectangle is " + r.area())
        r.onClick()
        var c = Circle(0.0, 0.0, 4.0)
        println("The area of circle is " + c.area())
        c.onClick()
        var t = Triangle(3.0, 4.0, 0.0)
        println("The area of triangle is " + t.area())
        t.onClick()

    }

    @Test
    fun testStudent2()
    {
        val student21 = Student2()
        student21.name = "Jack"
        student21.age = 29
        student21.sex = "M"
        println("student21 = ${student21}")
        val student22 = Student2("Tom", 25)
        student22.sex = "M"
        println("student22 = ${student22}")
        val student23 = Student2("Mary", 19, "F")
        println("student23 = ${student23}")
    }



    open class Foo
    class Goo : Foo()

    @Test
    fun testAs()
    {
        var foo = Foo()
        var goo = Goo()
        var h = goo as Foo
    }

    @Test
    fun testIs()
    {
        assertTrue("abc" is String)
        assertTrue(1 is Int)
        assertTrue(null is Any?)
        var len = strlen("abc")
        println(len)
        len = strlen(1)
        println(len)
        len = strlen('a')
        println(len)
        len = strlen(true)
        println(len)

    }

    @Test
    fun testList()
    {
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7)
        val filterList = list.filter { it % 3 !=0 }
        println(filterList)
        println(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()))
        println(testMax(10, 23))
        isLeapYear(2017)
        isLeapYear(2020)
        casesWhen(1)
        casesWhen("hello")
        casesWhen(true)
        casesWhen('a')
        casesWhen(101.45)
        casesWhen(356.89f)
        casesWhen(null)
    }


    fun <T> List<T>.filter(predicate: (T) -> Boolean) : MutableList<T> {
        val result = ArrayList<T>()
        this.forEach{
            if (predicate(it))
            {
                result.add(it)
            }
        }
        return result
    }



    @Test
    fun testFilterFun()
    {
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7)
        val result = list.filter { it % 2 == 1 }
        println(result)

    }


    var <T> MutableList<T>.firstElement : T
    get() {return this[0]}
    set(value) {this[0] = value}

    var <T> MutableList<T>.lastElement : T
    get() {return this[this.size - 1]}
    set(value) {this[this.size - 1] = value}

    @Test
    fun testMutableListFun()
    {
        val list = mutableListOf(1, 2, 3, 4, 5, 6, 7)
        println(list.firstElement)
        println(list.lastElement)
        list.firstElement = 10
        list.lastElement = 15
        println(list.firstElement)
        println(list.lastElement)
    }

    val funlist : List<(Int) -> Boolean> = listOf({ it -> it % 2 == 0 }, { it % 2 == 1 })

    @Test
    fun testListCollection()
    {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        println(list.filter(funlist[0]))
        println(list.filter(funlist[1]))
        val list2 = mutableListOf(10, 20, 30, 40, 50)
        println(list2.filter(funlist[0]))
        list2.add(0, 5)
        list2.add(6, 9)
        println(list2)
        println(list2.filter(funlist[1]))
        list2.forEach {
            print(" " + it)
        }
        println()
        list2.forEachIndexed { index, i ->  println("list index = ${index},value = ${i}")}
        println(list2.map { it * it })
        val strlist = listOf("a", "b", "c")
        val strlistmap = strlist.map { listOf(it + 1, it + 2, it + 3, it + 4) }
        println(strlistmap)
        val strlistmapflat = strlistmap.flatten()
        println(strlistmapflat)
        var strlistflatmap = strlist.flatMap { listOf(it + 1, it + 2, it + 3, it + 4) }
        println(strlistflatmap)
    }

    @Test
    fun testListFilter()
    {
        val studentList = listOf(
            Student4(1, "Jack", 18, 90),
            Student4(2, "Rose", 17, 80), Student4(3, "Alice", 16, 70)
        )
        println(studentList.filter { it.age >= 18 })
        println(studentList.filter { it.score <= 80 })
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        println(list.filterIndexed { index, i -> index % 2 == 0 && i > 3 })
        println(list.reversed())
        val list2 = listOf(1, 9, 3, 8, 5, 4, 7)
        println(list2.sorted())
        val dupList = listOf(1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5)
        println(dupList.distinct())
    }

    @Test
    fun testGenerator()
    {
        val gen = object : Generator<Int>{
            override fun next(): Int {
                return Random().nextInt(10)
            }

            override fun <T> console(t: T) {
                println(t)
            }
        }
        println(gen.next())
        gen.console(125)
        gen.console("aaa")

    }

    fun <T : Comparable<T>> gt(x: T, y: T) : Boolean{
        return x > y
    }

    @Test
    fun testgt()
    {
        println(gt(4, 8))
        println(gt(10, 8))
        println(gt("y", "a"))
        println(gt("abc", "d"))


    }

    fun String.inc() : String
    {
        var result = ""
        this.map { result += it + 1 }
        return result
    }

    fun getFileContent(filename: String) : String
    {
        val f = File(filename)
        return f.readText(Charset.forName("UTF-8"))

    }

    @Test
    fun testReadText()
    {
        val s = getFileContent("src/main/res/raw/a.txt")
        println(s)
    }

    fun getFileLines(filename: String) : List<String>
    {
        val f = File(filename)
        return f.readLines(Charset.forName("UTF-8"))
    }

    @Test
    fun testReadLine()
    {
        val l = getFileLines("src/main/res/raw/a.txt")
        println(l)
    }

    @Test
    fun testUseLine()
    {
        val myList = mutableListOf<String>()

        File("src/main/res/raw/a.txt").useLines { lines -> myList.addAll(lines) }

        myList.forEachIndexed { i, line -> println("${i}: " + line) }
    }

    @Test
    fun testInputStream()
    {
        val f = File("src/main/res/raw/a.txt")
        var ins : InputStream = f.inputStream()
        var content = ins.readBytes().toString(Charset.defaultCharset())
        println(content)

    }

    @Test
    fun testBufferedReader()
    {
        val f = File("src/main/res/raw/a.txt")

        var ins : InputStream = f.inputStream()
        BufferedReader(InputStreamReader(ins, "UTF-8")).use {
            for (s in it.lines())
               println(s)

        }
    }

    @Test
    fun testReadBytes()
    {
        val f = File("src/main/res/raw/a.txt")
        val bytes : ByteArray = f.readBytes()
        println(bytes)
        println(bytes.joinToString(separator = " "))
        println(bytes.toString(Charset.defaultCharset()))
    }


    fun writeFile(text: String, destFile: String)
    {
        val f = File(destFile)
        if (!f.exists())
        {
            f.createNewFile()
        }
        f.writeText(text, Charset.defaultCharset())
    }

    @Synchronized fun appendFile(text: String, destFile: String)
    {
        val f = File(destFile)
        if (!f.exists())
        {
            f.createNewFile()
        }
        f.appendText(text, Charset.defaultCharset())

    }

    fun appendFileSync(text: String, destFile: String) {
        val f = File(destFile)
        if (!f.exists()) {
            f.createNewFile()
        }
        synchronized(this){
            f.appendText(text, Charset.defaultCharset())
        }
    }

    @Volatile private var running = false
    fun start() {
        running = true
        thread(start = true) {
            while (running)
            {
                println("still running : ${Thread.currentThread()}")
            }
        }
    }

    fun stop(){
        running = false
        println("stopped : ${Thread.currentThread()}")
    }

    @Test
    fun testThreadFifth()
    {
         start()
         Thread.sleep(2000)
         stop()
    }

    @Test
    fun testWriteText()
    {
        writeFile("good morning", "src/main/res/raw/b.txt")
    }


    @Test
    fun testAppendText()
    {
        appendFile("\nAre you teacher?", "src/main/res/raw/b.txt")
    }

    @Test
    fun changeByteArrayToString()
    {
        val bytes : ByteArray = "Hello!".toByteArray()
        val string = bytes.toString(Charset.defaultCharset())
        println(string)
        val bytes2 = "How are you?".toByteArray()
        val string2 = String(bytes2)
        println(string2)
    }

    @Test
    fun changeStringToByteArray()
    {
        val s = "good morning"
        println(s)
        println(s.toByteArray(Charset.defaultCharset()))
    }

    fun writeUrlBytesTo(filename: String, url: String){
        val bytes = URL(url).readBytes()
        File(filename).writeBytes(bytes)
    }

    @Test
    fun testWriteUrlBytesTo()
    {
        writeUrlBytesTo("src/main/res/raw/list2.png", "https://www.hansoft.com.au/list2.png")
    }

    @Test
    fun testRegexMatches()
    {
        val r1 = Regex("[a-z]+")
        println(r1.matches("ABCabc"))
        println(r1.matches("abc"))
        val r2 = Regex("[a-z]+", RegexOption.IGNORE_CASE)
        println(r2.matches("ABCabc"))
        println(r2.matches("abc"))
        val r3 = "[A-Z]+".toRegex()
        println(r3.matches("ABC"))
        println(r3.matches("abc"))
    }

    @Test
    fun testRegexContainsMatchIn()
    {
        val r1 = Regex("[0-9]+")
        println(r1.containsMatchIn("123abc"))
        println(r1.containsMatchIn("abc"))
    }

    @Test
    fun testRegexMatchEntire()
    {
        var r1 = Regex("[0-9]+")
        println(r1.matchEntire("1234567890"))
        println(r1.matchEntire("1234567890")?.value)
        println(r1.matchEntire("1234567890!"))
    }

    @Test
    fun testRegexReplace()
    {
        var r1 = Regex("[0-9]+")
        var replace = r1.replace("12345xyz", "abcd")
        println(replace)
        var replace2 = r1.replace("345hjk8", { (it.value.toInt() * it.value.toInt()).toString() })
        println(replace2)
    }

    @Test
    fun testRegexFind()
    {
        var r1 = Regex("[0-9]+")
        println(r1.find("123xyz456opq789abc"))
        println(r1.find("123xyz456opq789abc")?.value)
    }

    @Test
    fun testRegexFindAll()
    {
        var r1 = Regex("[0-9]+")
        println(r1.findAll("123xyz456opq789abc"))
        r1.findAll("123xyz456opq789abc").forEach { println(it.value) }

    }

    @Test
    fun testRegexGroup()
    {
        val r1 = Regex("[0-9]+")
        val p = r1.toPattern()
        val m = p.matcher("888abc999")
        while (m.find())
        {
            val d = m.group()
            println(d)
        }
    }

    @Test
    fun testThread()
    {
        object : Thread() {
            override fun run() {
                println("A thread : ${Thread.currentThread()}")
            }
        }.start()
    }

    @Test
    fun testThreadSecond()
    {
        Thread({ println("B thread : ${Thread.currentThread()}") }).start()
    }

    @Test
    fun testThreadThird()
    {
        val t = Thread({
            println("C thread : ${Thread.currentThread()}")
        })
        t.isDaemon = false
        t.name = "CThread"
        t.priority = 3
        t.start()
    }

    @Test
    fun testThreadFourth()
    {
        thread(start = true, isDaemon = false, name = "DThread", priority = 3)
        {
            println("D thread : ${Thread.currentThread()}")
        }

    }


    fun appendBytes(array: ByteArray, destFile: String)
    {
        val f = File(destFile)
        if (!f.exists())
        {
            f.createNewFile()
        }
        f.appendText("\n", Charset.defaultCharset())
        f.appendBytes(array)

    }

    @Test
    fun testAppendBytes()
    {
        val startArray = byteArrayOf(0x1, 0x2, 0x3)
        appendBytes(startArray, "src/main/res/raw/b.txt")
    }

    @Test
    fun testBufferedWriter()
    {
        val f = File("src/main/res/raw/a.txt")

        val writer = f.bufferedWriter(Charset.defaultCharset(), 100)
        writer.use {
            it.write("how old are you")
        }
    }

    @Test
    fun testinc()
    {
        println("abc".inc())
    }

    fun traverseFileTree(filename: String)
    {
        val f = File(filename)
        val fileTreeWalk = f.walk()
        fileTreeWalk.iterator().forEach {
            println(it.absolutePath)
        }
    }

    @Test
    fun testTraverseFileTree()
    {
        traverseFileTree(".")
    }

    fun getFileIterator(filename: String) : Iterator<File> {
        val f = File(filename)
        val fileTreeWalk = f.walk()
        return fileTreeWalk.iterator()
    }

    @Test
    fun testGetFileIterator()
    {
        getFileIterator(".").forEach {
            println(it.isFile)
        }
    }


    fun getFileSequenceBy(filename: String, p: (File) -> Boolean):Sequence<File>{
        val f = File(filename)
        return f.walk().filter(p)
    }

    fun checkFile(f: File) : Boolean
    {


        if (f.isFile)
        {
            return true;
        }
        else
        {
            println("not file")
            return false
        }


    }

    @Test
    fun testGetFileSequenceBy()
    {

        getFileSequenceBy(".", { f: File -> checkFile(f) }).iterator().forEach {
            println(it.absolutePath)
        }
    }

    fun copyFile(filename: String)
    {
        val f = File(filename)
        f.copyRecursively(File("src/main/res/raw/c.txt"), true, { _, exception -> throw exception })
    }

    fun getUrlContent(url: String) : String {
        return URL(url).readText(Charset.defaultCharset())
    }

    fun getUrlBytes(url: String) : ByteArray {
        return URL(url).readBytes()
    }

    @Test
    fun testGetUrlContent()
    {
        println(getUrlContent("https://www.hansoft.com.au"))
    }


    @Test
    fun testCopyFile()
    {
        copyFile("src/main/res/raw/b.txt")
    }

    @Test
    fun testContainer()
    {
        val container = Container<Int, String>(1, "a")
        println(container)
        container.console(25)
        container.console("hello")
    }



    @Test
    fun testSetCollection()
    {
        val set = setOf("a", "b", "c", "d", "e")
        println(set)
        set.forEach { print(it + " ")}
        val set2 = mutableSetOf("a", "b", "c", "d", "e")
        set2.add("f")
        set2.add("g")
        set2.forEach { print(it + " ")}
        println()
        set2.forEachIndexed { index, s -> println("list index = ${index},value = ${s}")}
        println(set2.map { "item " + it })
        println(set2.reversed())
        val set3 = setOf("e", "b", "a", "c", "f")
        println(set3.sorted())

    }

    @Test
    fun testMapCollection()
    {
        val map = mapOf(1 to "name", 2 to "age", 3 to "sex")
        println(map)
        map.forEach{print("key = ${it.key}, value = ${it.value}, ")}
        val map2 = mutableMapOf(1 to "name", 2 to "age", 3 to "sex")
        map2.put(4, "score")
        map2.put(5, "salary")
        println()
        println(map2)
        println(map2.entries)
        map2.forEach{print("key = ${it.key}, value = ${it.value}, ")}
        println()
        map2.entries.forEach{print("key = ${it.key}, value = ${it.value}, ")}
        println()
        val map3 =  map2.map {"student " + it.value }
        val map4 = map2.map{it.key + 1 }
        println(map3)
        println(map4)
    }

    fun testMax(a: Int, b: Int) : Int
    {
        val max = if (a > b)
        {
            println("Max is a : " + a)
            a
        } else
        {
            println("Max is b : " + b)
            b
        }
        return max
    }

    fun isLeapYear(year: Int) : Boolean {

        if ((year % 4 == 0 && year % 100 != 0 ) || (year % 400 == 0 ))
        {
            println(year.toString() + " is leap year")
            return true
        }
        else
        {
            println(year.toString() + " is not leap year")
            return false
        }
    }

    fun casesWhen(obj: Any?)
    {
        when(obj) {
            in 0..9 -> println("$obj is a number")
            "hello" -> println("$obj is hello")
            is Char -> println("$obj is a char")
            is Boolean -> println("$obj is a Boolean")
            is Float -> println("$obj is a Float number")
            is Double -> println("$obj is a Double number")
            else -> println("$obj is default value")
        }
    }

    fun fact(n: Int) : Int {
        var result = 1
        when(n) {
            0, 1 -> result = 1
            else -> result = n * fact(n - 1)
        }
        return result
    }

    @Test
    fun testFact()
    {
        println("fact(10) = " + fact(10))
    }



}