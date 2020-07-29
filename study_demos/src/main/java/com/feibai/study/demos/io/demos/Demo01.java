package com.feibai.study.demos.io.demos;

import java.io.File;
import java.util.List;

public class Demo01 {


  /**
   * 遍历文件夹下的所有文件
   */
  public static void lsDirFiles(File file, List<String> filesList) {
    if (null == file || !file.exists()) {
      return;
    }
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      if (null != files) {
        for (File tmp : files) {
          lsDirFiles(tmp, filesList);
        }
      }
    } else if (file.isFile()) {
      filesList.add(file.getName());
    }
  }
}
