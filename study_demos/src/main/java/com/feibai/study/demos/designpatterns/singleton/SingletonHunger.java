package com.feibai.study.demos.designpatterns.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 饿汉式
 * <p>
 * 1.static变量会在类装载时初始化，此时不会涉及多个线程对象访问该对象的问题，虚拟机保证只会
 * 装载一次该类，肯定不会发生并发访问的问题，因此不需要synchronized关键字；
 * <p>
 * 2.调用效率高
 * <p>
 * 3.缺点：如果只是加载本类，而不是要调用getInstance()，甚至永远没有调用，则会造成资源浪费。
 *
 * @author feibai
 */
public class SingletonHunger implements Serializable {
  private static SingletonHunger instance = new SingletonHunger();

  // 私有构造器
  private SingletonHunger() {
    // 防止反射破解单例,一般不需要考虑
    if (instance != null) {
      throw new RuntimeException();
    }
  }

  // 反序列化时，如果定义了readSolve()方法则直接返回此方法指定的对象，而不是再创建新的对象
  private Object readResolve() throws ObjectStreamException {
    return instance;
  }

  // 静态获取方法，方法没有同步，调用效率高
  public static SingletonHunger getInstance() {
    return instance;
  }

  public static void main(String[] args) {
    System.out.println(SingletonHunger.getInstance());
    System.out.println(SingletonHunger.getInstance());
    System.out.println(SingletonHunger.getInstance());
  }

}
