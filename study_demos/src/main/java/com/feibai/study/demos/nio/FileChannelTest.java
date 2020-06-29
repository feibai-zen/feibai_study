package com.feibai.study.demos.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;

public class FileChannelTest {

  public static void main(String[] args) {
    // 4GB的数据
    File sourceFile = new File("d://dd.iso");
    File targetFile = new File("d://ee.iso");
    targetFile.deleteOnExit();
    try {
      targetFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // stream方式
    FileChannelTest.copyFileByStream(sourceFile, targetFile);

    // channel方式
    FileChannelTest.copyFileByFileChannel(sourceFile, targetFile);
  }

  /**
   * channel方式
   */
  public static void copyFileByFileChannel(File sourceFile, File targetFile) {
    Instant begin = Instant.now();

    RandomAccessFile randomAccessSourceFile;
    RandomAccessFile randomAccessTargetFile;
    try {
      // 构造RandomAccessFile，用于获取FileChannel
      randomAccessSourceFile = new RandomAccessFile(sourceFile, "r");
      randomAccessTargetFile = new RandomAccessFile(targetFile, "rw");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }

    FileChannel sourceFileChannel = randomAccessSourceFile.getChannel();
    FileChannel targetFileChannel = randomAccessTargetFile.getChannel();

    // 分配1MB的缓存空间
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
    try {
      while (sourceFileChannel.read(byteBuffer) != -1) {
        byteBuffer.flip();
        targetFileChannel.write(byteBuffer);
        byteBuffer.clear();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        sourceFileChannel.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        targetFileChannel.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    System.out.println("total spent " + Duration.between(begin, Instant.now()).toMillis());
  }

  /**
   * stream方式
   */
  public static void copyFileByStream(File sourceFile, File targetFile) {
    Instant begin = Instant.now();

    FileInputStream fis;
    FileOutputStream fos;
    try {
      fis = new FileInputStream(sourceFile);
      fos = new FileOutputStream(targetFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    // 使用byte数组读取方式，缓存1MB数据
    byte[] readed = new byte[1024 * 1024];
    try {
      while (fis.read(readed) != -1) {
        fos.write(readed);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        fis.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    System.out.println("total spent " + Duration.between(begin, Instant.now()).toMillis());
  }
}