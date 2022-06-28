package com.feibai.study.demos.multithread.cooperation.pipedchar;

import java.io.*;

/**
 * 读取线程先启动后，由于当时没有数据被写入，所以线程被阻塞在in.read(byteArray)，直到有数据写入，才继续往下执行
 * <p>
 * 写入线程先启动后，由于没有数据被读取，管道中的缓冲区大小超过1024byte后，写入数据out.write(bytes)方法将被阻塞。后续读取线程开启后，读取缓冲区数据后，写入线程还能继续写入
 */
public class Cooperation_PipedChar {

  public static void main(String[] args) {
    try {
      WriteData writeData = new WriteData();
      ReadData readData = new ReadData();

      PipedWriter out = new PipedWriter();
      PipedReader in = new PipedReader();

      //两个pipedStream建立通信连接
      in.connect(out);
//      out.connect(in);

      ThreadReader threadReader = new ThreadReader(in, readData, "thread-read");
      ThreadWriter threadWrite = new ThreadWriter(out, writeData, "thread-write");

      //先启动read线程
//      threadRead.start();
//      Thread.sleep(2000);
//      threadWrite.start();

      //先启动write线程
      threadWrite.start();
      Thread.sleep(12000);
      threadReader.start();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }

  }

}
