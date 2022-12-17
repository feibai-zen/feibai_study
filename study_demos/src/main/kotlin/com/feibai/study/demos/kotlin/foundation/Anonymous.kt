package com.feibai.study.demos.kotlin.foundation

class Anonymous {

    fun demo01() {
        val name = "feibai"
        val len = name.count()
        println(len)

        val len1 = name.count() {
            it == 'i'
        }
        println(len1)

        //括号可以省略
        val len2 = name.count {
            it == 'i'
        }
        println(len2)
    }

}

fun main() {
    val anonymous = Anonymous()
    anonymous.demo01()
}