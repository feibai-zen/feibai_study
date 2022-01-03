package study.feibai.demos.fundation

import java.util.function.Consumer


/**
 * 遍历list集合
 */
fun main() {
    val list: List<String> = mutableListOf("hello", "helloworld", "world")
    for (i in list) {
        println(i)
    }
    println("-----------------")
    list.forEach(Consumer { println(it) })
    println("-----------------")
    list.forEach(System.out::println)
    println("invoke fun traverList")
    traverList(list)

    array()
}

fun traverList(list: List<Any>?) {
    if (list == null || list.size == 0) {
        return;
    } else {
        for (i in list) {
            println("traver way1: $i")
        }

        println("-----------")
        list.forEach(Consumer { println("traver way2: $it") })

        println("-----------")
        list.forEach(System.out::println)

    }
}

fun array() {
    val arr = intArrayOf(1, 2, 3)//这里arr的引用不能改变，但是可以改变引用数组的内容
    arr.set(0, 4)
    for (item in arr) {
        println(item)
    }
    println("-------------")
    for (i: Int in arr.indices) {
        println("array[$i] = ${arr[i]}")
    }
    println("-------------")
    for ((index, value) in arr.withIndex()) {
        println("array[$index] = $value")
    }

}

