package study.feibai.demos.fundation

fun main() {
    val a = 5
    println(a in 0..10)
    print(a !in 0..10)

    println("---------")
    for (i in 0..10) {
        println(i)
    }
    println("---------")
    for (i in 0.rangeTo(10)) {
        println(i)
    }

    println("---------")
    for (i in 0..10 step 2) {
        println(i)
    }

    println("---------")
    for (i in 10 downTo 0 step 2) {
        println(i)
    }
}