package com.feibai.study.demos.multithread.foundation.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 创建线程
 * <p>
 * 实现Callable接口，重写call()方法
 *
 * @author feibai
 */
public class CallableThread implements Callable<Boolean> {

  private String url;
  private String name;

  public CallableThread(String url, String name) {
    this.url = url;
    this.name = name;
  }

  @Override
  public Boolean call() throws Exception {//实现call()方法，该方法可以有返回值，可以抛出异常
    WebDownloader webDownloader = new WebDownloader();
    webDownloader.download(this.url, this.name);
    return true;
  }
}
