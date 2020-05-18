package com.feibai.study.demos.demos;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class Test {

  public static void main(String[] args) {

    new Test().test_001();
  }

  public void test_001() {
    List<Integer> tempModelList = JSON.parseArray("[123,456,789]", Integer.class);
    System.out.println(tempModelList);
  }

}
