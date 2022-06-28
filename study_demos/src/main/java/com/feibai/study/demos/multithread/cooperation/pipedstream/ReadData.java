package com.feibai.study.demos.multithread.cooperation.pipedstream;

import java.io.IOException;
import java.io.PipedInputStream;

public class ReadData {

  public void readMethod(PipedInputStream in) {
    try {

      System.out.println("read :");
      byte[] byteArray = new byte[20];
      int readLength = in.read(byteArray);
      while (readLength != -1) {
        String newData = new String(byteArray, 0, readLength);
        System.out.println(newData);
        readLength = in.read(byteArray);
      }
      System.out.println();
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
