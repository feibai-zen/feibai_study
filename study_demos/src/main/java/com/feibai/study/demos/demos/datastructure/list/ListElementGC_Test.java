package com.feibai.study.demos.demos.datastructure.list;

import com.feibai.study.demos.demos.reflect.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListElementGC_Test {

  public static void main(String[] args) throws Exception {


  }

  public static void test_arrayList()throws Exception{

    List<Person> persons = new ArrayList<>();
    persons.add(new Person("", 11));
    persons.add(new Person("", 12));
    persons.add(new Person("", 13));

    persons.clear();

    System.gc();
    TimeUnit.SECONDS.sleep(20);

    BigDecimal bg = new BigDecimal("1.0");
    bg.intValueExact();

  }

  public static void test_null_element() {
    List<Person> persons = new ArrayList<>();
    persons.add(new Person("", 11));
    persons.add(new Person("", 12));
    persons.add(new Person("", 13));
    persons.add(null);



  }
}
