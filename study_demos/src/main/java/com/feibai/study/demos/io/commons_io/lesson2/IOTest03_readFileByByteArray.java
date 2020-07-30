package com.feibai.study.demos.io.commons_io.lesson2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 四个步骤: 分段读取 文件字节输入流
 * 1、创建源
 * 2、选择流
 * 3、操作
 * 4、释放资源
 */
public class IOTest03_readFileByByteArray {

  public static void main(String[] args) {
    //1、创建源
    File src = new File("abc.txt");
    //2、选择流
    InputStream is = null;
    try {
      is = new FileInputStream(src);
      //3、操作 (分段读取)
      byte[] flush = new byte[1024 * 10]; //缓冲容器
      int len = -1; //接收长度
      while ((len = is.read(flush)) != -1) {
        //字节数组-->字符串 (解码)
        String str = new String(flush, 0, len);//这里的len代表实际读取到的大小。如果最后读到字节数组的内容比字节数组的容量小，
        // 那么全部将字节数组中的内容编码成String，就会存在非法内容。
        System.out.println(str);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
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
