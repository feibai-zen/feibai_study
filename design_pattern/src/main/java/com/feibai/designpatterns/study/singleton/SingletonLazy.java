package com.feibai.designpatterns.study.singleton;

/**
 * 懒汉式 --1）延迟加载，资源利用率高了；2） 缺点：每次调用getInstance()方法都要同步，并发效率低
 *
 * @author feibai
 */
public class SingletonLazy {
  private static SingletonLazy instance;

  // 私有构造器
  private SingletonLazy() {

  }

  // 静态获取方法，
  public static synchronized SingletonLazy getInstance() {
    if (null == instance) {
      instance = new SingletonLazy();
    }
    return instance;
  }

  public static void main(String[] args) {
    System.out.println(SingletonLazy.getInstance());
    System.out.println(SingletonLazy.getInstance());
    System.out.println(SingletonLazy.getInstance());
  }

}
