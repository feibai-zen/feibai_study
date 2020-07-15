package com.feibai.designpatterns.study.templatemethod;

/**
 * 比较好的一个例子-https://www.jianshu.com/p/800a44c1d9dd
 */


public abstract class BankTemplateMethod {
  // 具体方法
  public void takeNumber() {
    System.out.println("取号排队");
  }

  public abstract void transact(); // 办理具体的业务 //钩子方法

  public void evaluate() {
    System.out.println("反馈评分");
  }

  public final void process() { // 模板方法！！！
    this.takeNumber();

    this.transact();

    this.evaluate();
  }

}
