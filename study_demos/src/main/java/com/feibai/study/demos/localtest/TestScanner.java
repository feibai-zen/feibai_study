package com.feibai.study.demos.localtest;

import java.util.Scanner;

/**
 * Scnner
 */
public class TestScanner {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("输入名字");
    String name = scanner.nextLine();
    System.out.println("请输入年龄");
    String age = scanner.nextLine();
    System.out.println("请输入地址");
    String address = scanner.nextLine();

    System.out.println(name + ":" + age + ":" + address);
  }
}
