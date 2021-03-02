package com.feibai.designpatterns.study.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 模拟通过反射、反序列化破解单例模式
 *
 * @author feibai
 */
public class AttackSingleton {

  public static void main(String[] args) throws Exception {

    SingletonHunger instance1 = SingletonHunger.getInstance();
    SingletonHunger instance2 = SingletonHunger.getInstance();
    System.out.println(instance1);
    System.out.println(instance2);
//通过反射方式调用私有构造器创建对象
//		Class<SingletonHunger> clazz = (Class<SingletonHunger>) Class
//				.forName("com.my.study.designpatterns.singleton.SingletonHunger");
//		Constructor<SingletonHunger> constructor = clazz.getDeclaredConstructor(null);
//		constructor.setAccessible(true);
//		SingletonHunger instance3 = constructor.newInstance();
//		SingletonHunger instance4 = constructor.newInstance();
//		System.out.println(instance3);
//		System.out.println(instance4);

//通过反序列化方式构造多个对象，这种方式只针对于实现了Serializable的单例类
    FileOutputStream fos = new FileOutputStream("singletonSerializable");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(instance1);
    oos.close();
    fos.close();

    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singletonSerializable"));
    SingletonHunger instance5 = (SingletonHunger) ois.readObject();
    System.out.println(instance5);
  }

}
