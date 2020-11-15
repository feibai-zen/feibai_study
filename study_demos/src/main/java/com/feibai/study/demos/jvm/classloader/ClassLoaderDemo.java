package com.feibai.study.demos.jvm.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * 自定义类加载器demo
 */
public class ClassLoaderDemo extends ClassLoader {

  private String classPath;

  public ClassLoaderDemo(String classPath) {
    this.classPath = classPath;
  }

  private byte[] loadByte(String name) throws Exception {
    name = name.replace("\\", "/");
    FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
    int len = fis.available();
    byte[] data = new byte[len];
    fis.read(data);
    fis.close();
    return data;
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    try {
      byte[] data = loadByte(name);
      return defineClass(name, data, 0, data.length);
    } catch (Exception e) {
      throw new ClassNotFoundException();
    }
  }

  public static void main(String[] args) throws Exception {
    ClassLoaderDemo classLoader = new ClassLoaderDemo("path");
    Class clazz = classLoader.loadClass("name");
    Object object = clazz.newInstance();

    Method helloMethod = clazz.getDeclaredMethod("", null);
    helloMethod.invoke(object, null);

  }

}
