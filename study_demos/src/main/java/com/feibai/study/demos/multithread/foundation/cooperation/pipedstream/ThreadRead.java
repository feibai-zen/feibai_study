package com.feibai.study.demos.multithread.foundation.cooperation.pipedstream;

import java.io.PipedInputStream;

public class ThreadRead extends Thread {

  private PipedInputStream in;
  private ReadData read;

  public ThreadRead(PipedInputStream in, ReadData read, String name) {
    super(name);
    this.in = in;
    this.read = read;
  }

  @Override
  public void run() {
    read.readMethod(in);
  }
}
