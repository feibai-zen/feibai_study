package com.feibai.study.demos.io.commons_io.lesson2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 第一个程序:理解操作步骤  标准
 * 1、创建源
 * 2、选择流
 * 3、操作
 * 4、释放资源
 */
public class IOTest02_readFileByOneByteWithLoop {

  public static void main(String[] args) {
    //1、创建源
    File src = new File("print.txt");
    //2、选择流
    InputStream is = null;
    try {
      is = new FileInputStream(src);
      //3、操作 (读取)
      int temp;
      while ((temp = is.read()) != -1) {
        System.out.print((char) temp);
        //如果读取文件遇到换行符，则会输出换行符。可以理解一个文件是一个很大的字节数组。文本编辑器之所
        // 以会换行，是因为，字节数组中存在换行符。文本编辑器解析换行符之后呈现在文本编辑器界面中
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
