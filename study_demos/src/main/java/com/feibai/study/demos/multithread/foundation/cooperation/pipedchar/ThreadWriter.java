package com.feibai.study.demos.multithread.foundation.cooperation.pipedchar;

import java.io.PipedOutputStream;
import java.io.PipedWriter;

public class ThreadWriter extends Thread {
  private WriteData write;
  private PipedWriter out;

  public ThreadWriter(PipedWriter out, WriteData write, String name) {
    super(name);
    this.write = write;
    this.out = out;
  }

  @Override
  public void run() {
    write.writeMethoid(out);
  }
}
