package com.feibai.study.demos.designpatterns.event;

import java.util.EventObject;

public class MethodMonitorEvent extends EventObject {
  // 时间戳，用于记录方法开始执行的时间
  public long timestamp;

  public MethodMonitorEvent(Object source) {
    super(source);
  }
}

