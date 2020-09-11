package com.feibai.study.demos.multithread.foundation.cooperation.pipedchar;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedReader;

public class ReadData {

  public void readMethod(PipedReader in) {
    try {

      System.out.println("read :");
      char[] charArray = new char[20];
      int readLength = in.read(charArray);
      while (readLength != -1) {
        String newData = new String(charArray, 0, readLength);
        System.out.println(newData);
        readLength = in.read(charArray);
      }
      System.out.println();
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
