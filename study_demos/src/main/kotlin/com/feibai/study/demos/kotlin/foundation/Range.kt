package com.feibai.study.demos.kotlin.foundation

//编译型常量
private const val PI = 3.1415926
class Range {

    fun range_test() {
        for (x in 0..5) {
            print("$x, ")
        }

        for (x in 0 until 5) {
            print("$x, ")
        }

        for (x in 'A'..'E') {
            print("$x, ")
        }

        for (x in 'A' until 'E') {
            print("$x, ")
        }

    }

    fun range_test02() {
        (5 downTo 1).joinToString(", ").run(::println)
        (1..10 step 3).joinToString(", ").run(::println)

        intArrayOf(1, 2, 3, 4, 5).indices.forEach(::println)
    }

    fun demo_test() {
        var testScore = 80
        var grade = when (testScore) {
            in 90..100 -> "优秀"
            in 80..89 -> "良好"
            in 60..79 -> "及格"
            in 0..59 -> "不及格"
            else -> "分数异常"
        }
        println("Grade = " + grade)

        if (testScore !in 60..100) {
            println("分数异常")
        }
    }
}

fun main(){
    val range = Range()
    range.range_test02()
}