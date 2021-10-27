package com.feibai.study.demos.designpatterns.templatemethod;

/**
 * 比较好的一个例子-https://www.jianshu.com/p/800a44c1d9dd
 * <p>
 * 定义一个操作算法中的框架，而将这些步骤延迟加载到子类中。
 * <p>
 * 它的本质就是固定算法框架。
 * <p>
 * 1.2 解决何种问题
 * 让父类控制子类方法的调用顺序
 * <p>
 * 模板方法模式使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
 * <p>
 * 1.3 模式好处
 * 开发人员在开发时，只需要考虑方法的实现。不需要考虑方法在何种情况下被调用。实现代码复用。
 * <p>
 * 1.4 模式适合场景
 * 一次性实现一个算法的不变部分，并将可变的行为留给子类来实现。
 * 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复。
 * 需要通过子类来决定父类算法中某个步骤是否执行，实现子类对父类的反向控制。
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

  public final void process() { // 模板方法. 控制了子类方法的调用顺序
    this.takeNumber();

    this.transact();

    this.evaluate();
  }

}
