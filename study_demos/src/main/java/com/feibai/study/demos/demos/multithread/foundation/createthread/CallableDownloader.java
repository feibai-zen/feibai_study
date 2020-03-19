package com.feibai.study.demos.demos.multithread.foundation.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 创建线程的方式3，实现Callable接口， 重写call()方法
 *
 * @author leeyuanlong
 */
public class CallableDownloader implements Callable<Boolean> {

  private String url;
  private String name;

  public CallableDownloader(String url, String name) {
    this.url = url;
    this.name = name;

  }

  @Override
  public Boolean call() throws Exception {//实现call()方法，该方法可以有返回值
    WebDownloader webDownloader = new WebDownloader();
    webDownloader.download(this.url, this.name);
    return true;
  }

  public static void main(String[] args) throws Exception {
    CallableDownloader cd1 = new CallableDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
    CallableDownloader cd2 = new CallableDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13", "spl.jpg");
    CallableDownloader cd3 = new CallableDownloader(
            "http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg",
            "success.jpg");
        // 创建执行服务
    ExecutorService ser = Executors.newFixedThreadPool(3);
    Future<Boolean> result1 = ser.submit(cd1);
    Future<Boolean> result2 = ser.submit(cd2);
    Future<Boolean> result3 = ser.submit(cd3);
    boolean r1 = result1.get();
    boolean r2 = result2.get();
    boolean r3 = result3.get();
    ser.shutdownNow();

  }

}
