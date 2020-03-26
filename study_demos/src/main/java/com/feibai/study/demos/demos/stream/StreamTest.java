package com.feibai.study.demos.demos.stream;

import com.feibai.study.demos.beans.Employee;
import com.feibai.study.demos.beans.Medal;
import com.google.common.collect.Lists;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
  public static void main(String[] args) {

    List<Employee> employees = new ArrayList<>();
    List<Integer> ids = employees.stream().map(Employee::getId).collect(Collectors.toList());

    employees.stream().map(Employee::getId).collect(Collectors.toList());
    employees.stream().distinct().filter(Objects::nonNull).collect(Collectors.toList());

  }

  private static void construct_stream() {
    // 构造流
    // 1. Individual values
    Stream stream = Stream.of("a", "b", "c");
    // 2. Arrays
    String[] strArray = new String[]{"a", "b", "c"};
    stream = Stream.of(strArray);
    stream = Arrays.stream(strArray);
    // 3. Collections
    List<String> list = Arrays.asList(strArray);
    stream = list.stream();
    stream.collect(Collectors.toList());
  }

  public static void test_01() {
    List<Medal> medalList = Lists.newArrayList();
    medalList.stream().filter(tuple -> tuple.getEndAt() > System.currentTimeMillis())
            .map(tuple -> tuple.getTagNo().toString()).distinct().collect(Collectors.toList());
  }

}
