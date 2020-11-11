package com.feibai.study.demos.jvm.classloader.demo07_01;

/**
 * 非主动使用类字段演示
 * <p>
 * output:
 * 代码运行之后，只会输出“SuperClass init！”，而不会输出“SubClass init！”。对于静态字段，只有直接定义这个字段的类才会被初始化，
 * 因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
 */
public class NotInitialization {
  public static void main(String[] args) {
    System.out.println(SubClass.value);
  }

}
