package study.feibai.demos.fundation

import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

/**
 * 函数demos
 */
fun add(a: Int, b: Int): Int {
    return a + b
}

fun add1(a: Int, b: Int) = a + b

/**
 * Unit表示函数不返回任何类型。
 * 也可以省略掉Unit，Kotlin会进行返回值推断
 */
fun myPrint(): Unit {
    println("this is myPrint function. Unit means no return value.")
}

/**
 * 字符串模板
 */
fun addPrint(a: Int, b: Int) {
    println("$a + $b = ${a + b}")
}

fun main() {
    val utcTime = "2021-08-10T02:10:00.825Z"
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    val utcDate = sdf.parse(utcTime)
    sdf.timeZone = TimeZone.getDefault()
    val localDate = sdf.parse(sdf.format(utcDate.time))

    val time = ZonedDateTime.ofInstant(Instant.ofEpochMilli(utcDate.time), ZoneId.of("UTC")).toLocalDateTime()


    println(localDate)
    println(time)
}

/**
 * if(...) x else y结构
 */
fun max() {
    val x: Int = 10
    val y: Int = 20
    var max: Int = if (x > y) x else y
}

fun max1() {
    val x: Int = 10
    val y: Int = 20
    var max: Int = if (x > y) {
        println("max value is x = $x")
        x//语句块的最后一行作为返回值
    } else {
        println("max value is y = $y")
        y//语句块的最后一行作为返回值
    }
}

/**
 * 返回值可能为null，声明为Int?
 */
fun multify(a: String, b: String): Int? {
    try {
        return a.toInt() * b.toInt()
    } catch (e: NumberFormatException) {
        return null
    }
}

/**
 * String转换成Int
 */
fun convertToInt(numberStr: String): Int? {
    try {
        return numberStr.toInt()
    } catch (e: NumberFormatException) {
        return null
    }
}