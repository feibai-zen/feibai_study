package study.feibai.demos.fundation

fun main() {
//    stringTest("test")
    val  testString = "abcd#ef"
    println(testString.split("#"))
}

/**
 * 三个双引号中的字符不进行转义
 */
fun stringTest(strPrefix: String) {
    var str: String = """
        \n
        \n
        \n
        |
        |
        |
    """

    println(strPrefix + str)

}