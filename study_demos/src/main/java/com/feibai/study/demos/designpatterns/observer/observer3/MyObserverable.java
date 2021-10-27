package com.feibai.study.demos.designpatterns.observer.observer3;

/**
 * 被观察者接口定义
 */
public interface MyObserverable {

  void register(MyObserver myObserver);

  void remove(MyObserver myObserver);

  void send(NewsModel model);

}
