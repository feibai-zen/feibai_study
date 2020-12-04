package com.feibai.study.demos.jvm.chapter8;

/**
 * 字段不参与多态
 *
 * @author zzm
 * 输出：
 * I am Son,  i have $0
 * I am Son,  i have $4
 * This gay has $2
 * <p>
 * <p>
 * 分析：
 * 输出两句都是“I am Son”，这是因为Son类在创建的时候，首先隐式调用了Father的构造函数，而Father构造函数中对showMeTheMoney()的调用是一次虚方法
 * 调用，实际执行的版本是Son::showMeTheMoney()方法，所以输出的是“I am Son”。而这时候虽然父类的money字段已经被初始化成2了，但Son::showMeTheMoney()
 * 方法中访问的却是子类的money字段，这时候结果自然还是0，因为它要到子类的构造函数执行时才会被初始化。main()的最后一句通过静态类型访问到了父类中的money，输出了2。
 */

public class FieldHasNoPolymorphic {

  static class Father {
    public int money = 1;

    public Father() {
      money = 2;
      showMeTheMoney();
    }

    public void showMeTheMoney() {
      System.out.println("I am Father, i have $" + money);
    }
  }

  static class Son extends Father {
    public int money = 3;

    public Son() {
      money = 4;
      showMeTheMoney();
    }

    @Override
    public void showMeTheMoney() {
      System.out.println("I am Son,  i have $" + money);
    }
  }

  public static void main(String[] args) {
    Father gay = new Son();
    System.out.println("This gay has $" + gay.money);
  }
}