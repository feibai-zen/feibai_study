package com.feibai.study.demos.demos.optional;

import com.feibai.study.demos.beans.User;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @ClassName OptionalDemos
 * @Description JDK8 Optional的使用。对空值进行疯转，减少了业务对空的判断。
 * @author feibai
 * @Date 2019/10/30 5:55 下午
 **/

public class OptionalDemos {

  public static void main(String[] args) {
    //usage
    Optional<User> person = getSomeValue3();
    System.out.println(person.isPresent());

  }

  // 返回一个空的Optional类型;
  private static Optional<User> getSomeValue1() {

    return Optional.empty();

  }

  // 使用这个方法，值不可以为空，否则抛exception
  private static Optional<User> getSomeValue2() {
    User value = new User();
    return Optional.of(value);
  }

  // 使用这个方法，值可以为空，如果为空返回Optional.empty
  private static Optional<User> getSomeValue3() {

    User value = new User();
    return Optional.ofNullable(value);
  }

  /**
   * 使用ifPresent()
   *
   * 这里ifPresent()是将一个Lambda表达式作为输入，T值如果不为空将传入这个lambda。那么这个lambda将不为空的单词转为大写输出显示。在前面names单词流寻找结果中，
   * 有可能找不到开始字母为L的单词，返回为空，也可能找到不为空，这两种情况都传入lambda中，无需我们打开盒子自己编写代码来判断，它自动帮助我们完成了，无需人工干预。
   */
  private static void test01(){
    Stream<String> names = Stream.of("Lamurudu", "Okanbi", "Oduduwa");

    Optional<String> longest = names.filter(name -> name.startsWith("L")).findFirst();

    longest.ifPresent(name -> {
      String s = name.toUpperCase();
      System.out.println("The longest name is "+ s);
    });
  }

  /**
   *使用map()
   *
   *使用Optional<T>的map方法能够返回另外一个Optional，如上面的 LnameInCaps，因为传入map()的参数值也许会导致一个空值。
   */
  private static void test02(){
    Stream<String> names = Stream.of("Lamurudu", "Okanbi", "Oduduwa");
    Optional<String> longest = names.filter(name -> name.startsWith("L")).findFirst();
    Optional<String> lNameInCaps = longest.map(String::toUpperCase);
  }

  /**
   * 使用orElse()
   *
   * 如果在T可能空时你需要一个值的话，那么可以使用 orElse()，它能在T值存在的情况下返回这个值，否则返回输入值。
   */
  private static void test03(){
    Stream<String> names = Stream.of("Lamurudu", "Okanbi", "Oduduwa");

    Optional<String> longest = names.filter(name -> name.startsWith("Q")).findFirst();
    String alternate = longest.orElse("Nimrod");
    System.out.println(alternate); //prints out "Nimrod"
  }

  /**
   * 使用orElseGet()
   *
   * orElseGet() 方法类似于orElse()，但是不是直接返回输入参数，而是调用输入参数，返回调用的结果，这个输入参数通常是lambda
   */
  private static void test04(){
    Stream<String> names = Stream.of("Lamurudu", "Okanbi", "Oduduwa");

    Optional<String> longest = names.filter(name -> name.startsWith("Q")).findFirst();
    String alternate = longest.orElseGet(() -> {
      // perform some interesting code operation
      // then return the alternate value.
      return "Nimrod";

    });
    System.out.println(alternate);
  }

  /**
   * 使用 orElseThrow()
   * 　　
   * orElseThrow()是在当遭遇Null时，决定抛出哪个Exception时使用
   */
  private static void test05() throws Exception {
    Stream<String> names = Stream.of("Lamurudu", "Okanbi", "Oduduwa");

    Optional<String> longest = names.filter(name -> name.startsWith("Q")).findFirst();
    longest.orElseThrow(Exception::new);
  }

}
