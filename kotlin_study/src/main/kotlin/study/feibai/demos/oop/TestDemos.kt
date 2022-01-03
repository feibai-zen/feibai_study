package study.feibai.demos.oop

/**
 * 默认为public，且不要求类名与文件名相同
 *
 * 一个类可以有一个primary构造方法以及一个或多个secondary构造方法
 *
 * primary构造方法是类头（class heInader）的一部分，它位于类名后面，可以拥有若干参数
 *
 * 如果primary构造方法没有任何注解或者可见性关键字修饰，那么constructor关键字可以省略
 */
//class EmptyClass
//
//class MyClass constructor(userName: String) {
//    private val userName: String = userName.toUpperCase()
//
//    init {
//        //构造方法代码块
//        println(userName)
//    }
//
//}

fun main() {
//    var myClass = MyClass("leeyuanlong")
    val a = "liyuanlong"
    val b = "liyuanlong"
    println(a==b)
    println(a.equals(b))
}