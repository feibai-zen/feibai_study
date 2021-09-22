package com.feibai.spring.study.multithread.foundation.cooperation.pipedstream;

import java.io.IOException;
import java.io.PipedOutputStream;

public class WriteData {
  public void writeMethoid(PipedOutputStream out) {
    try {
      System.out.println("write :");
      for (int i = 0; i < 300000; i++) {
        String outData = "," + (i + 1);
        out.write(outData.getBytes());
        System.out.println(outData);
      }
      System.out.println();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
