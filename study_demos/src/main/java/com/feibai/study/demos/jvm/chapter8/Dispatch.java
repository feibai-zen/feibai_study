package com.feibai.study.demos.jvm.chapter8;

/**
 * 单分派、多分派演示
 *
 * @author zzm
 * <p>
 * 输出：
 * father choose 360
 * son choose qq
 * <p>
 * 分析：
 * <p>
 * 在main()里调用了两次hardChoice()方法，这两次hardChoice()方法的选择结果在程序输出中已经显示得很清楚了。我们关注的首先是编译阶段中编译器的选择过程，
 * 也就是静态分派的过程。这时候选择目标方法的依据有两点：一是静态类型是Father还是Son，二是方法参数是QQ还是360。这次选择结果的最终产物是产生了两条invokevirtual指令，
 * 两条指令的参数分别为常量池中指向Father::hardChoice(360)及Father::hardChoice(QQ)方法的符号引用。因为是根据两个宗量进行选择，所以Java语言的静态分派属于多分派类型。
 * <p>
 * 再看看运行阶段中虚拟机的选择，也就是动态分派的过程。在执行“son.hardChoice(new QQ())”这行代码时，更准确地说，是在执行这行代码所对应的invokevirtual
 * 指令时，由于编译期已经决定目标方法的签名必须为hardChoice(QQ)，虚拟机此时不会关心传递过来的参数“QQ”到底是“腾讯QQ”还是“奇瑞QQ”，因为这时候参数的静态类型、
 * 实际类型都对方法的选择不会构成任何影响，唯一可以影响虚拟机选择的因素只有该方法的接受者的实际类型是Father还是Son。因为只有一个宗量作为选择依据，所以Java语
 * 言的动态分派属于单分派类型。根据上述论证的结果，我们可以总结一句：如今（直至本书编写的Java 12和预览版的Java 13）的Java语言是一门静态多分派、动态单分派的语言。
 */

public class Dispatch {

  static class QQ {
  }

  static class _360 {
  }

  public static class Father {

    public void hardChoice(QQ arg) {
      System.out.println("father choose qq");
    }

    public void hardChoice(_360 arg) {
      System.out.println("father choose 360");
    }
  }

  public static class Son extends Father {

    @Override
    public void hardChoice(QQ arg) {
      System.out.println("son choose qq");
    }

    @Override
    public void hardChoice(_360 arg) {
      System.out.println("son choose 360");
    }
  }

  public static void main(String[] args) {
    Father father = new Father();
    Father son = new Son();
    father.hardChoice(new _360());
    son.hardChoice(new QQ());
  }
}