package com.feibai.study.demos.demos.foundation.base_class_type;

/**
 * @ClassName IntegerCompareWithInt
 * @Description Integer与int
 * @Author leeyuanlong
 * @Date 2019/11/25 10:25 上午
 **/


/**
 *Integer与int类型的关系
 * Integer是int的包装类，int的默认值是0，而Integer的默认值是null（我们经常在代码中使用的Integer.valueOf（） 和xx.intValue（）就是自动装箱和拆箱的过程 ）,
 * 需要注意的是Integer里面默认的缓存数字是-128-127，Integer与Integer相互比较，数据在-128-127范围内，就会从缓存中拿去数据，比较就相等；如果不在这个范围，就会直
 * 接新创建一个Integer对象，使用== 判断的是两个内存的应用地址，所以自然不相等。Integer和int类型相比，在jdk1.5,会自动拆箱，然后比较栈内存中的数据。
 *
 * 1）int跟int比较
 * int数据类型，都是在栈内存中存储，如果这个数字在栈内存中存在就会直接指向这个内存地址，如果不存在，就会重新开辟内存空间，所以int和int类型的比较，相同的值不会存在
 * 内存不等的情况。八个基本数据类型不能看作对象（这点很特殊），存放在栈中。栈内操作速度快，创建销毁很容易
 *
 * 2）int跟Integer比较
 * 会自动拆箱，变成int与int对比
 *
 * 3）Integer跟Integer比较
 * 上面提到Integer里面会有缓存数字，在-128~127的范围内，==会相等，获取的cache的地址。不在上述范围内，会去new一个对象对比,直接new对象的话，肯定不等于
 */
public class IntegerCompareWithInt {

  public static void main(String[] args) {
    test01();
    test02();

  }

  public static void test01(){
    System.out.println("=============test01===========");
    int a = 500;
    Integer integer_b = null;
    System.out.println(integer_b.equals(a));
    System.out.println();

  }

  public static void test02(){
    System.out.println("=============test02===========");
    int a = 200;
    Integer b = 200;
    System.out.println(a == b);//会自动拆箱
    Integer c = 100;
    Integer d = 100;
    System.out.println(c == d);//在Integer缓存范围内，对比的是值
    c = 200;
    d = 200;
    System.out.println(c == d);//超出Integer缓存范围，对比的对象的内存地址

  }

}