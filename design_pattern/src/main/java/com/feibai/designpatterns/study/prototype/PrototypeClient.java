package com.feibai.designpatterns.study.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 1）浅拷贝 2）深拷贝 3）通过序列化--反序列化实现深拷贝
 *
 * @author feibai
 */
public class PrototypeClient {
  public static void main(String[] args) throws Exception {
//		lowCopy();
    deepCopy();
//		deepCopyWithSerialize();
//		compareEfficient();

  }

  // 浅拷贝，内层对象还是相同
  public static void lowCopy() throws Exception {
    Date date = new Date(12312321331L);
    Sheep s1 = new Sheep("少利", date);
    System.out.println(s1);
    System.out.println(s1.getSname());
    System.out.println(s1.getBirthday());

    date.setTime(23432432423L);

    System.out.println(s1.getBirthday());

    Sheep s2 = (Sheep) s1.clone();
    s2.setSname("多利");
    System.out.println(s2);
    System.out.println(s2.getSname());
    System.out.println(s2.getBirthday());
    System.out.println(s1.getSname());
  }

  // 深拷贝，内层对象分离，完全是新的对象
  public static void deepCopy() throws CloneNotSupportedException {
    Date date = new Date(12312321331L);
    Sheep2 s1 = new Sheep2("少利", date);
    Sheep2 s2 = (Sheep2) s1.clone(); // 实现深复制。s2对象的birthday是一个新对象！

    System.out.println(s1);
    System.out.println(s1.getSname());
    System.out.println(s1.getBirthday());

    date.setTime(23432432423L);

    System.out.println(s1.getBirthday());

    s2.setSname("多利");
    System.out.println(s2);
    System.out.println(s2.getSname());
    System.out.println(s2.getBirthday());

  }

  // 原型模式(深复制,使用序列化和反序列化的方式实现深复制)
  public static void deepCopyWithSerialize() throws Exception {
    Date date = new Date(12312321331L);
    Sheep s1 = new Sheep("少利", date);
    System.out.println(s1);
    System.out.println(s1.getSname());
    System.out.println(s1.getBirthday());

//		使用序列化和反序列化实现深复制
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(s1);
    byte[] bytes = bos.toByteArray();

    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
    ObjectInputStream ois = new ObjectInputStream(bis);

    Sheep s2 = (Sheep) ois.readObject(); // 克隆好的对象！

    System.out.println("修改原型对象的属性值");
    date.setTime(23432432423L);

    System.out.println(s1.getBirthday());

    s2.setSname("多利");
    System.out.println(s2);
    System.out.println(s2.getSname());
    System.out.println(s2.getBirthday());
  }

  // 测试普通new方式创建对象和clone方式创建对象的效率差异！ 如果需要短时间创建大量对象，并且new的过程比较耗时。则可以考虑使用原型模式！
  public static void compareEfficient() throws Exception {
    testNew(1000);
    testClone(1000);
  }

  private static void testNew(int size) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < size; i++) {
      Laptop t = new Laptop();
    }
    long end = System.currentTimeMillis();
    System.out.println("new的方式创建耗时：" + (end - start));
  }

  private static void testClone(int size) throws CloneNotSupportedException {
    long start = System.currentTimeMillis();
    Laptop t = new Laptop();
    for (int i = 0; i < size; i++) {
      Laptop temp = (Laptop) t.clone();
    }
    long end = System.currentTimeMillis();
    System.out.println("clone的方式创建耗时：" + (end - start));
  }

}

class Laptop implements Cloneable { // 笔记本电脑
  public Laptop() {
    try {
      Thread.sleep(10); // 模拟创建对象耗时的过程!
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    Object obj = super.clone(); // 直接调用object对象的clone()方法！
    return obj;
  }

}
