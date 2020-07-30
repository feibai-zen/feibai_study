package com.feibai.study.demos.io.commons_io.lesson1;

import java.io.File;

/**
 *
 */
public class FileDemo02 {
  /**
   * 构建File对象
   * 相对路径与绝对路径
   * 1)、存在盘符: 绝对路径
   * 2)、不存在盘符:相对路径  ,当前目录 user.dir
   *
   * @param args
   */
  public static void main(String[] args) {
    String path = "D:/java300/IO_study01/IO.png";

    //绝对路径
    File src = new File(path);
    System.out.println(src.getAbsolutePath());

    //相对路径
    System.out.println(System.getProperty("user.dir"));
    src = new File("IO.png");
    System.out.println(src.getAbsolutePath());


    //构建一个不存在的文件
    src = new File("aaa/IO2.png");
    System.out.println(src.getAbsolutePath());
  }

}
