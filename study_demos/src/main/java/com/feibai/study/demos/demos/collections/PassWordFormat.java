package com.feibai.study.demos.demos.collections;

import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * 只要重写writeObject和readObject，
 * <p>
 * 非transinent修饰的变量使用defaultWriteObject()和defaultReadObject()进行序列化和反序列化
 * transient修饰的变量必须手动使用writeObject()和readObject()进行序列化和反序列化
 */

public class PassWordFormat implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private transient String password;

  public PassWordFormat(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public PassWordFormat() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private void writeObject(ObjectOutputStream oos) throws IOException {//重写以下两个方法，完成序列化时password的加密和解密
    oos.defaultWriteObject();
    char a[] = this.password.toCharArray();
    String str = "";
    for (int i = 0; i < a.length; i++) {
      a[i] += i;
      str += a[i];
    }//各项加i再逆置；
    StringBuilder sb = new StringBuilder(str);
    oos.writeObject(sb.reverse().toString());
  }

  private void readObject(ObjectInputStream ois)
          throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
    String str = (String) ois.readObject();
    System.out.println("解密后：" + str);
    StringBuilder sb = new StringBuilder(str);
    sb.reverse();
    char a[] = sb.toString().toCharArray();
    str = "";
    for (int i = 0; i < a.length; i++) {
      a[i] -= i;
      str += a[i];
    }
    this.password = str;
  }

  @Override
  public String toString() {
    return "PasswordFormat [name=" + name + ", password=" + password + "]";
  }

  public static void serializeTemp(PassWordFormat ob, File file) {
    try (
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
    ) {
      oos.writeObject(ob);
      oos.flush();
      System.out.println((PassWordFormat) ois.readObject());
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    PassWordFormat pf = new PassWordFormat("xcw", "xcw12345678");
    File file = new File("./yj.txt");
    PassWordFormat.serializeTemp(pf, file);
  }
}
