package com.feibai.study.demos.io.commons_io.lesson3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 四个步骤: 分段读取 文件字符输入流  加入缓冲流
 * 1、创建源
 * 2、选择流
 * 3、操作
 * 4、释放资源
 */
public class BufferedTest03 {

  public static void main(String[] args) {
    //1、创建源
    File src = new File("abc.txt");
    //2、选择流
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(src));
      //3、操作 (分段读取)
      String line = null;
      while ((line = reader.readLine()) != null) {
        //字符数组-->字符串
        System.out.println(line);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //4、释放资源
      try {
        if (null != reader) {
          reader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
