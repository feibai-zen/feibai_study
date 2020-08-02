package com.feibai.study.demos.io.commons_io.lesson3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件字符输出流 加入缓冲流
 * 1、创建源
 * 2、选择流
 * 3、操作(写出内容)
 * 4、释放资源
 */
public class BufferedTest04_BufferedWriter {

  public static void main(String[] args) {
    //1、创建源
    File dest = new File("dest.txt");
    //2、选择流
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(dest));
      //3、操作(写出)
      writer.append("IO is so easy");
      writer.newLine();//换行
      writer.append("尚学堂欢迎你");
      writer.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      //4、释放资源
      try {
        if (null != writer) {
          writer.close();
        }
      } catch (Exception e) {
      }
    }
  }

}
