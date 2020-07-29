package com.feibai.study.demos.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class TestIO {
  public static void writeContentToFile(String filePath, String msg) {
    File file = new File(filePath);

    OutputStream os = null;
    try {
      os = new FileOutputStream(file, true);
      byte[] datas = msg.getBytes();
      os.write(datas, 0, datas.length);
      os.flush();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != os) {
        try {
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void decodeMsg() {
    try {
      String msg = "性命生命使命a";
      // 编码: 字节数组
      byte[] datas = msg.getBytes(); // 默认使用工程的字符集

      // 解码: 字符串 String​(byte[] bytes, int offset, int length, String charsetName)
      msg = new String(datas, 0, datas.length, "utf8");
      System.out.println("Test01: " + msg);

      // 乱码:
      // 1)、字节数不够
      msg = new String(datas, 0, datas.length - 2, "utf8");
      System.out.println("Test02: " + msg);
      msg = new String(datas, 0, datas.length - 1, "utf8");
      System.out.println("Test03: " + msg);

      // 2)、字符集不统一
      msg = new String(datas, 0, datas.length - 1, "gbk");
      System.out.println("Test04: " + msg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void encodeMsg() {
    try {
      String msg = "性命生命使命a";
      // 编码: 字节数组
      byte[] datas = msg.getBytes(); // 默认使用工程的字符集
      System.out.println(datas.length);
      System.out.println(Arrays.toString(datas));
      System.out.println("------------------------------");

      // 编码: 其他字符集
      datas = msg.getBytes("UTF-16LE");
      System.out.println(datas.length);
      System.out.println(Arrays.toString(datas));

      System.out.println("------------------------------");
      datas = msg.getBytes("GBK");
      System.out.println(datas.length);
      System.out.println(Arrays.toString(datas));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void readFileContent(String filePath) {
    File file = new File(filePath);
    InputStream is = null;
    try {
      is = new FileInputStream(file);
      int tmp = 0;
      while ((tmp = is.read()) != -1) {
        System.out.println((char) tmp);
      }
    } catch (Exception e) {
    } finally {
      if (null != is) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void readFileContentByBlock(String filePath) {
    File file = new File(filePath);
    InputStream is = null;
    try {
      is = new FileInputStream(file);
      byte[] flush = new byte[1024 * 1];
      int len = 0;
      while ((len = is.read(flush)) != -1) {
        System.out.println(new String(flush, 0, len));// 注意这里解码的时候，读取多少字节就解码多少字节
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (null != is) {
        try {
          is.close();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    writeContentToFile("testFileOutput", "IO is so easy.\r\n");
  }

}
