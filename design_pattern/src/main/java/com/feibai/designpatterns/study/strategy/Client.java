package com.feibai.designpatterns.study.strategy;

public class Client {
  public static void main(String[] args) {
    Strategy s1 = new OldCustomerManyStrategy();
    Context ctx = new Context(s1);

    ctx.printPrice(998);

  }
}
