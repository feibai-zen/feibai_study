package com.feibai.study.demos.io.commons_io;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class CommonIOTest {

  public static void main(String[] args) throws Exception {

    // 文件大小
    long len = FileUtils.sizeOf(new File("/Users/xmly/Leeyuanlong/feibai_study/study_demos/image.jpg"));
    System.out.println(len);
    // 目录大小
    long dirlen = FileUtils.sizeOf(new File("/Users/xmly/Leeyuanlong/feibai_study/study_demos/lib"));
    System.out.println(dirlen);

    Collection<File> files = FileUtils.listFiles(new File("D:/BaiduNetdiskDownload"), EmptyFileFilter.NOT_EMPTY,
            null);// 列出目录下所有文件；最后一个参数为null，则只扫描一层目录
    for (File file : files) {
      System.out.println(file.getAbsolutePath());
    }
    System.out.println("---------------------");
    files = FileUtils.listFiles(new File("D:/BaiduNetdiskDownload"), EmptyFileFilter.NOT_EMPTY,
            DirectoryFileFilter.INSTANCE);
    for (File file : files) {
      System.out.println(file.getAbsolutePath());
    }
    System.out.println("---------------------");
    files = FileUtils.listFiles(new File("D:/workspace/my-study-demos"), new SuffixFileFilter("java"),
            DirectoryFileFilter.INSTANCE);
    for (File file : files) {
      System.out.println(file.getAbsolutePath());
    }
    System.out.println("---------------------");
    files = FileUtils.listFiles(new File("D:/workspace/my-study-demos"),
            FileFilterUtils.or(new SuffixFileFilter("java"), new SuffixFileFilter("class"), EmptyFileFilter.EMPTY),
            DirectoryFileFilter.INSTANCE);
    for (File file : files) {
      System.out.println(file.getAbsolutePath());
    }

    System.out.println("---------------------");
    files = FileUtils.listFiles(new File("D:/workspace/my-study-demos"),
            FileFilterUtils.and(new SuffixFileFilter("java"), EmptyFileFilter.NOT_EMPTY),
            DirectoryFileFilter.INSTANCE);
    for (File file : files) {
      System.out.println(file.getAbsolutePath());
    }

    // 读取文件
    String msg = FileUtils.readFileToString(new File("emp.txt"), "UTF-8");
    System.out.println(msg);
    byte[] datas = FileUtils.readFileToByteArray(new File("emp.txt"));
    System.out.println(datas.length);

    // 逐行读取
    List<String> msgs = FileUtils.readLines(new File("emp.txt"), "UTF-8");
    for (String string : msgs) {
      System.out.println(string);
    }
    LineIterator it = FileUtils.lineIterator(new File("emp.txt"), "UTF-8");
    while (it.hasNext()) {
      System.out.println(it.nextLine());
    }

    // 写出文件
    FileUtils.write(new File("happy.sxt"), "学习是一件伟大的事业\r\n", "UTF-8");
    FileUtils.writeStringToFile(new File("happy.sxt"), "学习是一件辛苦的事业\r\n", "UTF-8", true);
    FileUtils.writeByteArrayToFile(new File("happy.sxt"), "学习是一件幸福的事业\r\n".getBytes("UTF-8"), true);

    // 写出列表
    List<String> datasList = new ArrayList<String>();
    datasList.add("马云");
    datasList.add("马化腾");
    datasList.add("弼马温");

    FileUtils.writeLines(new File("happy.sxt"), datasList, "。。。。。", true);// 元素之间的连接符

    // 复制文件
    FileUtils.copyFile(new File("p.png"), new File("p-copy.png"));
    // 复制文件到目录
    FileUtils.copyFileToDirectory(new File("p.png"), new File("lib"));
    // 复制目录到目录
    FileUtils.copyDirectoryToDirectory(new File("lib"), new File("lib2"));
    // 复制目录
    FileUtils.copyDirectory(new File("lib"), new File("lib2"));
    // 拷贝URL内容
    String url = "https://pic2.zhimg.com/v2-7d01cab20858648cbf62333a7988e6d0_qhd.jpg";
    FileUtils.copyURLToFile(new URL(url), new File("marvel.jpg"));
    String datastring = IOUtils.toString(new URL("http://www.163.com"), "gbk");
    System.out.println(datastring);

  }

  public void createDir() {
    File file = new File("/Users/xmly/Test/test/test/");
    if (!file.exists()) {
      file.mkdirs();
    }
    System.out.println(file.getAbsoluteFile());
  }

}
