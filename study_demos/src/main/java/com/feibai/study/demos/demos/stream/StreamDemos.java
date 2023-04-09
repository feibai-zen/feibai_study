package com.feibai.study.demos.demos.stream;

import com.feibai.study.demos.beans.Employee;
import com.feibai.study.demos.beans.Medal;
import com.feibai.study.demos.beans.Student;
import com.feibai.study.demos.beans.User;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * 包含了大部分的中间和终止操作。
 * 中间操作（此类型方法返回的都是Stream）:map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
 * 终止操作：forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
 */

/**
 * Stream和Collection的区别主要有：
 * 1）Stream 本身并不存储数据，数据存储在对应的collection中，或者在需要的时候才生成
 * 2）Stream 不会修改数据源，总是返回新的Stream
 * 3)
 */
public class StreamDemos {
    public static void main(String[] args) {
        test_stream_skip();
        test_stream_min();
    }

    private static void construct_stream() {
        // 构造流
        // 1. Individual values
        Stream stream =  Stream.of("a", "b", "c");
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
        medalList.stream().filter(element -> element.getEndAt() > System.currentTimeMillis())
                .map(tuple -> tuple.getTagNo().toString()).distinct().collect(Collectors.toList());
    }

    public static void test_02() {
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 10));

        List<Integer> list_after = list.stream().filter(item -> item > 5).collect(Collectors.toList());
        System.out.println(list_after.toString());
    }

    private static void test_03() {
        List<Employee> employees = new ArrayList<>();
        List<Integer> ids = employees.stream().map(Employee::getId).collect(Collectors.toList());

        employees.stream().map(Employee::getId).collect(Collectors.toList());
        employees.stream().distinct().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * distinct()
     */
    private static void test_distinct() {
        System.out.println(Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1).stream().distinct().collect(Collectors.toList()));

        //简单字符串的去重
        List<String> list = Arrays.asList("111", "222", "333", "111", "222");
        list.stream().distinct().forEach(System.out::println);


        //引用对象的去重，引用对象要实现hashCode和equal方法，否则去重无效
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        Student s5 = new Student(1L, "肖战", 15, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.stream().distinct().forEach(System.out::println);
    }

    /**
     * sort
     */
    private void test_sorted() {
        List<User> users = new ArrayList();
        List<User> giftTypeList = users.stream().distinct().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());

        List<String> list = Arrays.asList("333", "222", "111");
        list.stream().sorted().forEach(System.out::println);


        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.stream().sorted((stu1, stu2) -> Long.compare(stu2.getId(), stu1.getId())).sorted((stu1, stu2) -> Integer.compare(stu2.getAge(), stu1.getAge())).forEach(System.out::println);

    }

    private static void test_filter() {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        //筛选年龄大于15岁的学生
//        return students.stream().filter(s -> s.getAge()>15).collect(Collectors.toList());
        //筛选住在浙江省的学生
        students.stream().filter(s -> "浙江".equals(s.getAddress())).collect(Collectors.toList()).forEach(System.out::println);
    }

    /**
     * map
     */
    private static void test_stream_map() {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        testMap(students);
    }

    private static void testMap(List<Student> students) {
        //在地址前面加上部分信息，只获取地址输出
        List<String> addresses = students.stream().map(s -> "住址:" + s.getAddress()).collect(Collectors.toList());
        addresses.forEach(a -> System.out.println(a));
    }

    private static void test_stream_limit() {

        List<String> list = Arrays.asList("333", "222", "111");
        list.stream().limit(2).forEach(System.out::println);
    }

    /**
     * 集合skip，删除前n个元素
     */
    private static void test_stream_skip() {

        List<String> list = Arrays.asList("333", "222", "111");
        list.stream().skip(2).forEach(System.out::println);
    }

    /**
     * reduce--聚合
     */
    private static void test_stream_reduce() {
        //集合reduce,将集合中每个元素聚合成一条数据
        List<String> list = Arrays.asList("欢", "迎", "你");
        String appendStr = list.stream().reduce("北京", (a, b) -> a + b);
        System.out.println(appendStr);
    }

    /**
     * min--最小值
     * max--最大值
     */
    private static void test_stream_min() {
        //求集合中元素的最小值
        Student s1 = new Student(1L, "肖战", 14, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        Student minS = students.stream().min((stu1, stu2) -> Integer.compare(stu1.getAge(), stu2.getAge())).get();
        System.out.println(minS.toString());
    }

    /**
     * match--匹配
     * anyMatch：Stream 中任意一个元素符合传入的 predicate，返回 true
     * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     */
    private static void test_stream_match() {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        Boolean anyMatch = students.stream().anyMatch(s -> "湖北".equals(s.getAddress()));
        if (anyMatch) {
            System.out.println("有湖北人");
        }
        Boolean allMatch = students.stream().allMatch(s -> s.getAge() >= 15);
        if (allMatch) {
            System.out.println("所有学生都满15周岁");
        }
        Boolean noneMatch = students.stream().noneMatch(s -> "杨洋".equals(s.getName()));
        if (noneMatch) {
            System.out.println("没有叫杨洋的同学");
        }
    }

    /**
     * 统计：一些产生统计结果的收集器也非常有用，主要用于int, double, long基本类型
     */
    private void test_summaryStatistic() {
        List<Integer> list = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stat = list.stream().mapToInt((x) -> x * x).summaryStatistics();
        stat.getAverage();
        stat.getCount();
        stat.getMax();
        stat.getMin();
    }

}