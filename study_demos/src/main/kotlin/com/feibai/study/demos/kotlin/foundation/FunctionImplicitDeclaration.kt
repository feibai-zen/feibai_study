package com.feibai.study.demos.kotlin.foundation

/**
 * 匿名函数等价于Lambda表达式
 */
class FunctionImplicitDeclaration {

    fun demo01() {
        //1. 函数输入输出的声明
        val methodAction: () -> String

        //2. 对上面声明的函数进行实现
        methodAction = {
            val inputValue = 9999
            "$inputValue Feibai"
            // 匿名函数不要写return, 最后一行就是返回值
        }
        // 3. 调用函数
        println(methodAction())
    }

    fun demo02() {
        //1. 函数输入输出的声明
        //2. 对上面声明的函数进行实现
        val methodAction: (Int, Int, Int) -> String = { number1, number2, number3 ->
            val inputValue = 9999
            "$inputValue, $number1, $number2, $number3"
            // 匿名函数不要写return, 最后一行就是返回值
        }
        //3. 调用函数
        println(methodAction(1, 2, 3))
    }

    /**
     * 匿名函数 参数 & 返回值 自动类型推断
     */
    fun demo03() {
        //匿名函数，类型推断为String
        val method1 = { v1: Double, v2: Float, v3: Int ->
            "v1: $v1, v2: $v2, v3: $v3"
        }
        println(method1(5345.45435, 34535.4f, 99))

        //匿名函数，类型推断为Int
        val method2 = { v1: Double, v2: Float, v3: Int ->
            1
        }
        println(method2(5345.45435, 34535.4f, 99))

        val method3 = { v1: Double, v2: Float, v3: Int ->
            3456.7f
        }
        println(method3(5345.45435, 34535.4f, 99))


        //返回值自动推断为Any类型
        val weekResultMethod = { number: Int ->
            when (number) {
                1 -> "Monday"
                2 -> "Tuesday"
                3 -> "Wednesday"
                4 -> "Thursday"
                5 -> "Saturday"
                6 -> "Sunday"
                else -> -1
            }
        }

        println(weekResultMethod(7))
    }

}

//函数隐式声明 & 数据隐式返回
fun main() {
    val fid = FunctionImplicitDeclaration()
    fid.demo01()

    fid.demo02()

    fid.demo03()
}
