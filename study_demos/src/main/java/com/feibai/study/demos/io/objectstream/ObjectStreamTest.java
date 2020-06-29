package com.feibai.study.demos.io.objectstream;

import java.io.*;

/**
 * 测试使用ObjectStream
 * @author feibai
 */
public class ObjectStreamTest {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    String string = "hello. this is test of ObjectStream.";
    //写入ObjectStream
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(string);

    //从ObjectStream中写出
    byte[] bytes = bos.toByteArray();
    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
    ObjectInputStream ois = new ObjectInputStream(bis);
    String output = (String) ois.readObject();
    System.out.println(output);
  }
}
