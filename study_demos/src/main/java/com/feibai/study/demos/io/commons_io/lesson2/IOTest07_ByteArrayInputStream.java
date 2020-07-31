package com.feibai.study.demos.io.commons_io.lesson2;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 四个步骤:字节数组输入流
 * 1、创建源: 字节数组 不要太大
 * 2、选择流
 * 3、操作
 * 4、释放资源: 可以不用处理，close()是个空方法
 */
public class IOTest07_ByteArrayInputStream {

  public static void main(String[] args) {
    //1、创建源
    byte[] src = "talk is cheap show me the code".getBytes();
    //2、选择流
    InputStream is = null;
    try {
      is = new ByteArrayInputStream(src);
      //3、操作 (分段读取)
      byte[] flush = new byte[5]; //缓冲容器
      int len = -1; //接收长度
      while ((len = is.read(flush)) != -1) {
        //字节数组-->字符串 (解码)
        String str = new String(flush, 0, len);
        System.out.println(str);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //4、释放资源
      try {
        if (null != is) {
          is.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
