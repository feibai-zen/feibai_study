package com.feibai.designpatterns.study.proxy.proxydynamic1;

import java.lang.reflect.Proxy;

/**
 * 动态代理与静态代理相比较，最大的好处是接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理（InvocationHandler.invoke）。这样，在
 * 接口方法数量比较多的时候，我们可以进行灵活处理，而不需要像静态代理那样每一个方法进行中转。而且动态代理的应用使我们的类职责更加单一，复用性更强
 */
public class ProxyDynamicClient {
  public static void main(String[] args) {

    Star realStar = new Jay();
    StarHandler handler = new StarHandler(realStar);

    Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Star.class},
            handler);

    proxy.bookTicket();
    System.out.println("=============================");
    proxy.sing();

  }

  public void test() {
    Star star = new Jay();
    StarHandler handler = new StarHandler(star);

    Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Star.class}, handler);
    proxy.bookTicket();
    proxy.sing();

  }
}