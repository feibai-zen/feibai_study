package com.feibai.study.demos.nio;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将文件直接映射到虚拟内存
 *
 * 比较好的参考文档：https://www.jianshu.com/p/f90866dcbffc
 */
public class MappedByteBufferTest01 {

  /**
   * 运行结果：
   * 14235
   * Read time :24ms
   * Write time :21ms
   * 我们把标注1和2语句注释掉，换成它们下面的被注释的那条语句，再来看运行效果。14235
   * Read time :2ms
   * Write time :0ms
   */
  public static void main(String[] args) throws Exception {
    ByteBuffer byteBuf = ByteBuffer.allocate(1024 * 14 * 1024);
    byte[] bbb = new byte[14 * 1024 * 1024];
    FileInputStream fis = new FileInputStream("e://data/other/UltraEdit_17.00.0.1035_SC.exe");
    FileOutputStream fos = new FileOutputStream("e://data/other/outFile.txt");
    FileChannel fc = fis.getChannel();

    long timeStar = System.currentTimeMillis();// 得到当前的时间
    fc.read(byteBuf);// 1 读取
    //MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
    System.out.println(fc.size() / 1024);

    long timeEnd = System.currentTimeMillis();// 得到当前的时间
    System.out.println("Read time :" + (timeEnd - timeStar) + "ms");
    timeStar = System.currentTimeMillis();
    fos.write(bbb);//2.写入
    //mbb.flip();
    timeEnd = System.currentTimeMillis();

    System.out.println("Write time :" + (timeEnd - timeStar) + "ms");
    fos.flush();
    fc.close();
    fis.close();
  }


  //文件复制
  public void copyFile(String filename, String srcpath, String destpath) throws IOException {
    File source = new File(srcpath + "/" + filename);
    File dest = new File(destpath + "/" + filename);
    FileChannel in = null, out = null;
    try {
      in = new FileInputStream(source).getChannel();
      out = new FileOutputStream(dest).getChannel();
      long size = in.size();
      MappedByteBuffer buf = in.map(FileChannel.MapMode.READ_ONLY, 0, size);
      out.write(buf);
      in.close();
      out.close();
      source.delete();//文件复制完成后，删除源文件
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      in.close();
      out.close();
    }
  }

}
