package com.feibai.study.demos.demos.foundation.base_class_type;


/**
 * java 8种基础类型测试
 */
public class FoundationTypeTest {

  public static void main(String[] args) {
    float f = 3.4F;//3.4是double类型，赋值给float类型会损失精度

    short s1 = 1;
    s1 = (short) (s1 + 1);
    s1 += 1;

    /**
     * 由于1是int类型,因此s1 + 1运算结果也是int型,需要强制转换类型才能赋值给short型。而short s1 = 1;s1 += 1;
     * 可以正确编译,因为s1 += 1;相当于s1 = (short)(s1 +1);其中有隐含的强制类型转换。
     */
  }

}
