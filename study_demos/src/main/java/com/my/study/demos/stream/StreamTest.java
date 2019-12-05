package com.my.study.demos.stream;

import com.my.study.beans.Employee;

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

  private static void construct_stream(){
    // 构造流
    // 1. Individual values
    Stream stream = Stream.of("a", "b", "c");
    // 2. Arrays
    String [] strArray = new String[] {"a", "b", "c"};
    stream = Stream.of(strArray);
    stream = Arrays.stream(strArray);
    // 3. Collections
    List<String> list = Arrays.asList(strArray);
    stream = list.stream();
    stream.collect(Collectors.toList());
  }

}
