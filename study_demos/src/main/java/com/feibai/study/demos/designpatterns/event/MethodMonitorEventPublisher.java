package com.feibai.study.demos.designpatterns.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MethodMonitorEventPublisher {

  private final List<MethodMonitorEventListener> listeners = new ArrayList<MethodMonitorEventListener>();

  public void methodMonitor() throws InterruptedException {
    MethodMonitorEvent eventObject = new MethodMonitorEvent(this);
    publishEvent("begin", eventObject);
    // 模拟方法执行：休眠5秒钟
    TimeUnit.SECONDS.sleep(5);
    publishEvent("end", eventObject);

  }

  private void publishEvent(String status, MethodMonitorEvent event) {
    // 避免在事件处理期间，监听器被移除，这里为了安全做一个复制操作
    List<MethodMonitorEventListener> copyListeners = new ArrayList<MethodMonitorEventListener>(listeners);
    for (MethodMonitorEventListener listener : copyListeners) {
      if ("begin".equals(status)) {
        listener.onMethodBegin(event);
      } else {
        listener.onMethodEnd(event);
      }
    }
  }

  public void addEventListener(MethodMonitorEventListener listener) {
    listeners.add(listener);
  }

  public void removeEventListener(MethodMonitorEventListener listener) {
    //线程安全的删除监听器
  }

  public void removeAllListeners() {
    listeners.clear();
  }

  public static void main(String[] args) throws InterruptedException {
    MethodMonitorEventPublisher publisher = new MethodMonitorEventPublisher();
    publisher.addEventListener(new AbstractMethodMonitorEventListener());
    publisher.methodMonitor();
  }

}