package com.feibai.study.demos.multithread.foundation.cooperation.pipedstream;

import java.io.PipedOutputStream;

public class ThreadWrite extends Thread {
  private WriteData write;
  private PipedOutputStream out;

  public ThreadWrite(PipedOutputStream out, WriteData write, String name) {
    super(name);
    this.write = write;
    this.out = out;
  }

  @Override
  public void run() {
    write.writeMethoid(out);
  }
}
