package study.feibai.demos.fundation

fun main() {
    var list = listOf<String>("hello", "world", "hello world", "welcome", "goodbye")

    for (item in list) {
        println(item)
    }
    //Kotlin函数式编程, it代表当前正在操作的元素
    list.filter { it.length > 5 }
        .map { it.toUpperCase() }
        .sorted()
        .forEach { println(it) }
}