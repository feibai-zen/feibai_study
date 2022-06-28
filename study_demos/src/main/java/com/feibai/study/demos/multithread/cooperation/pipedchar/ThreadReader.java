package com.feibai.study.demos.multithread.cooperation.pipedchar;

import java.io.PipedReader;

public class ThreadReader extends Thread {

  private PipedReader in;
  private ReadData read;

  public ThreadReader(PipedReader in, ReadData read, String name) {
    super(name);
    this.in = in;
    this.read = read;
  }

  @Override
  public void run() {
    read.readMethod(in);
  }
}
