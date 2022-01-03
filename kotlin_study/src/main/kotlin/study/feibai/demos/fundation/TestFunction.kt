package study.feibai.demos.fundation

fun main() {
    testFunction("liyuanlong")
}


fun testFunction(name: String) {
    test(name) {
        println(name)
    }
}

fun test(name: String, fetch: () -> Unit) {
    println("before"+name)
    println("----------------")
    fetch()
}
