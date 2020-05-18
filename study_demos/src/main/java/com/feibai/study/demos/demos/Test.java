package com.feibai.study.demos.demos;

import java.math.BigDecimal;
import java.util.Arrays;

public class Test {

  public static void main(String[] args) {
    BigDecimal innerValue = BigDecimal.valueOf(100.00000);
    System.out.println(String.valueOf(innerValue.longValueExact()));
//    System.out.println(Long.parseLong("20.0"));

    System.out.println(BigDecimal.valueOf(20).multiply(new BigDecimal("0.2")).toString());
  }

}
