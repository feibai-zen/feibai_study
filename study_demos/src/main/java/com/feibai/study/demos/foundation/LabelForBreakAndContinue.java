package com.feibai.study.demos.foundation;

/**
 * label: 就是标签  要终止的位置
 * <p>
 * break label ：终止结束到标签 结束语句
 * <p>
 * continue label: 终止本次循环 跳到标签位置进行接下来的循环
 */
public class LabelForBreakAndContinue {

  public static void main(String[] args) {

  }

  private void test_01() {
    for (int i = 0; i < 5; i++) {
      Label_out:
      for (int j = 0; j < 5; j++) {
        if (j == 3) {
          break Label_out;
        }
        System.out.print("" + i + j + " ");
      }
      System.out.println();
    }
    System.out.println("This is end");
  }

  private void test_02() {
    Label_out:
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (j == 3) {
          break Label_out;
        }
        System.out.print("" + i + j + " ");
      }
      System.out.println();
    }
  }

  private void test_03() {
    for (int i = 0; i < 5; i++) {
      Label_out:
      for (int j = 0; j < 5; j++) {
        if (j == 3) {
          continue Label_out;
        }
        System.out.print("" + i + j + " ");
      }
      System.out.println();
    }
    System.out.println("This is end");
  }

  private void test_04() {
    Label_out:
    for (int i = 0; i < 5; i++) {

      for (int j = 0; j < 5; j++) {
        if (j == 3) {
          continue Label_out;
        }
        System.out.print("" + i + j + " ");
      }
      System.out.println();
    }
    System.out.println("This is end");
  }

}