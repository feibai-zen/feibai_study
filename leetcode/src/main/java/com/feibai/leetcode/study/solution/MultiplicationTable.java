package com.feibai.leetcode.study.solution;

public class MultiplicationTable {

  public static void main(String[] args) {
    printMultiplicationTable();
  }

  public static void printMultiplicationTable() {
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= i; j++) {
        System.out.print(j + " X " + i + " = " + i * j);
        System.out.print("\t");
      }
      System.out.println();
    }

  }
}
