package com.feibai.designpatterns.study.facade;

/**
 * 外观（Facade）模式是“迪米特法则”的典型应用，它有以下主要优点。
 * 降低了子系统与客户端之间的耦合度，使得子系统的变化不会影响调用它的客户类。
 * 对客户屏蔽了子系统组件，减少了客户处理的对象数目，并使得子系统使用起来更加容易。
 * 降低了大型软件系统中的编译依赖性，简化了系统在不同平台之间的移植过程，因为编译一个子系统不会影响其他的子系统，也不会影响外观对象。
 * <p>
 * 外观（Facade）模式的主要缺点如下。
 * 不能很好地限制客户使用子系统类。
 * 增加新的子系统可能需要修改外观类或客户端的源代码，违背了“开闭原则”。
 */
public class Client1 {
  public static void main(String[] args) {
//		工商局  a = new 海淀区工商局();
//		a.checkName();
//		质检局 b = new 海淀质检局();
//		b.orgCodeCertificate();
//		税务局  c  = new 海淀税务局();
//		c.taxCertificate();
//		银行  d = new 中国工商银行();
//		d.openAccount();

    new RegisterFacade().register();

  }
}
