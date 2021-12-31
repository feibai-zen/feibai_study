package com.feibai.study.demos.designpatterns.event;

import lombok.ToString;

// 2、事件监听接口的实现：如何处理
@ToString
public class AbstractMethodMonitorEventListener implements MethodMonitorEventListener {

  @Override
  public void onMethodBegin(MethodMonitorEvent event) {
    // 记录方法开始执行时的时间
    event.timestamp = System.currentTimeMillis();
  }

  @Override
  public void onMethodEnd(MethodMonitorEvent event) {
    // 计算方法耗时
    long duration = System.currentTimeMillis() - event.timestamp;
    System.out.println("耗时：" + duration);
  }
}