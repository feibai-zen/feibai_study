package com.feibai.study.demos.io.commons_io.lesson3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 对象流:
 * 1、写出后读取
 * 2、读取的顺序与写出保持一致
 * 3、不是所有的对象都可以序列化Serializable
 * <p>
 * ObjectOutputStream
 * ObjectInputStream
 *
 * @author TW
 */
public class ObjectTest {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    //写出 -->序列化
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos));
    //操作数据类型 +数据
    oos.writeUTF("编码辛酸泪");
    oos.writeInt(18);
    oos.writeBoolean(false);
    oos.writeChar('a');
    //对象
    oos.writeObject("谁解其中味");
    oos.writeObject(new Date());
    Employee emp = new Employee("马云", 400);
    oos.writeObject(emp);
    oos.flush();
    byte[] datas = baos.toByteArray();
    System.out.println(datas.length);
    //读取 -->反序列化
    ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
    //顺序与写出一致
    String msg = ois.readUTF();
    int age = ois.readInt();
    boolean flag = ois.readBoolean();
    char ch = ois.readChar();
    System.out.println(flag);
    //对象的数据还原
    Object str = ois.readObject();
    Object date = ois.readObject();
    Object employee = ois.readObject();

    if (str instanceof String) {
      String strObj = (String) str;
      System.out.println(strObj);
    }
    if (date instanceof Date) {
      Date dateObj = (Date) date;
      System.out.println(dateObj);
    }
    if (employee instanceof Employee) {
      Employee empObj = (Employee) employee;
      System.out.println(empObj.getName() + "-->" + empObj.getSalary());
    }

  }

}

//javabean 封装数据
class Employee implements java.io.Serializable {
  private transient String name; //该数据不需要序列化
  private double salary;

  public Employee() {
  }

  public Employee(String name, double salary) {
    this.name = name;
    this.salary = salary;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

}
