package com.feibai.study.demos.designpatterns.singleton;

/**
 * 静态内部类实现单例模式
 * <p>
 * 1.外部类没有static属性，则不会像饿汉式那样立即加载对象
 * 2.只有调用getInstance()，才会加载静态内部类。加载类时是线程安全的。instance是static final类
 * 型，保证了内存中只有一个实例存在，而且只能被赋值一次，从而保证了线程安全性。
 * 3.兼备了高效调用和延迟加载的优势。加载SingletonInnerStaticClass类时，不会初始化静态内部类，因此具有懒加载。
 *
 * @author feibai
 */
public class SingletonInnerStaticClass {

  private SingletonInnerStaticClass() {
  }

  private static class Inner {
    //静态内部类可以访问外层类
    private static final SingletonInnerStaticClass INSTANCE =
        new SingletonInnerStaticClass();
  }

  public static SingletonInnerStaticClass getInstance() {

    return Inner.INSTANCE;
  }

}
