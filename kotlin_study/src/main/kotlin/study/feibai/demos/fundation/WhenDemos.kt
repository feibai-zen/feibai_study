package study.feibai.demos.fundation


val HELLO_WORLD = "hello world"
fun main() {

    println("--------")
    println(whenTest01("hello"))
    println(whenTest01("world"))
    println(whenTest01(HELLO_WORLD))
    println(whenTest02("haha"))
    println("-------")
    println(whenTest02("hello"))
}

/**
 * similar to switch
 */
fun whenTest01(str: String): String {
    when (str) {
        "hello" -> return "HELLO"
        "world" -> return "WORLD"
        "hello world" -> return HELLO_WORLD
        else -> return "other input"
    }
}

fun whenTest02(str: String): String {
    return when (str) {
        "hello" -> "HELLO"
        "world" -> "WORLD"
        "hello world" -> HELLO_WORLD
        else -> "other input"
    }
}

fun whenTest03(str: String): String =
    when (str) {
        "hello" -> "HELLO"
        "world" -> "WORLD"
        "hello world" -> HELLO_WORLD
        else -> "other input"
    }

fun whenTest04(num: Int): String =
    when (num) {
        1 -> {
            println("1")
            "1"
        }
        2 -> {
            println("2")
            "2"
        }
        3, 4, 5 -> {
            print("3,4,5")
            "3,4,5"
        }
        in 6..10 -> {
            //Kotlin的..表示范围闭区间
            println("6,7,8,9,10")
            "6,7,8,9,10"
        }
        else -> "zzZ~"
    }

fun whenTest05() {
    var list = listOf<String>("hello", "world", "hello world", "welcome", "goodbye")

    when {
        "world" in list -> println("world is in list collection")
    }

}