package com.feibai.study.demos.io.commons_io.lesson2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 文件字符输出流
 * 1、创建源
 * 2、选择流
 * 3、操作(写出内容)
 * 4、释放资源
 */
public class IOTest06 {

  public static void main(String[] args) {
    //1、创建源
    File dest = new File("dest.txt");
    //2、选择流
    Writer writer = null;
    try {
      writer = new FileWriter(dest);
      //3、操作(写出)
      //写法一
//			String msg ="IO is so easy\r\n尚学堂欢迎你";
//			char[] datas =msg.toCharArray(); // 字符串-->字符数组
//			writer.write(datas,0,datas.length);
      //写法二
			/*String msg ="IO is so easy\r\n尚学堂欢迎你";
			writer.write(msg);	
			writer.write("add");		
			writer.flush();*/

      //写法三
      writer.append("IO is so easy\r\n").append("尚学堂欢迎你");
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
