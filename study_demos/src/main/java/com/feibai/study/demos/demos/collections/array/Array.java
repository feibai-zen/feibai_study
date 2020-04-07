package com.feibai.study.demos.demos.collections.array;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 特性简介：
 * 1.相同数据类型，分配在堆中、连续空间、有序，数组可以看做是对象，数组中的元素可以看做对象的成员属性值。数组一旦创建，容量就无法改变
 * 2.int []arr = new int[10];
 * arr在栈中。创建数组过程中默认初始化为成员属性的默认值。int默认0，boolean默认为false，引用默认为null。
 * 3.数组拷贝 Arrays.copyOf(),底层调用 System.arraycopy()
 * <p>
 * 优点：简单线性序列、快速访问数组元素，效率高。如果从效率和类型检查的角度看，数组是最好的
 * <p>
 * 缺点：空间固定，一旦创建了数组之后，容量就保持不变了
 */
public class Array {

  public static void main(String[] args) {
    Array instance = new Array();
    instance.test_07();

  }

  /**
   * 反转数组元素
   */
  public void test_01() {
    int[] intArray = {1, 2, 3, 4, 5};
    ArrayUtils.reverse(intArray);
  }

  /**
   * 移除数组元素
   */
  public void test_02() {
    int[] intArray = {1, 2, 3, 4, 5};
    int[] removed = ArrayUtils.removeElement(intArray, 3);//create a new array
  }

  /**
   * 数组转成Set
   */
  public void test_03() {
    String[] stringArray = new String[]{"a", "b"};
    Set<String> set = new HashSet<String>(Arrays.asList(stringArray));
  }

  /**
   * 把数组中的元素用指定的分隔符连接起来
   */
  public void test_04() {
    String j = StringUtils.join(new String[]{"a", "b", "c"}, ", ");
  }

  /**
   * 合并数组
   */
  public void test_05() {
    int[] intArray = {1, 2, 3, 4, 5};
    int[] intArray2 = {6, 7, 8, 9, 10};
    int[] combinedIntArray = ArrayUtils.addAll(intArray, intArray2);
  }

  /**
   * 检查数组是否包含某一个值
   */
  public void test_06() {
    String[] stringArray = new String[]{"a", "b"};
    boolean b = Arrays.asList(stringArray).contains("a");
  }

  /**
   * 测试元素可以为null
   */
  public void test_07() {
    String[] arr = {null, null};
    System.out.println(Arrays.toString(arr));

  }

  public void test_08() {
    Object[] a1 = {1001, "feibai", 18};
    Object[] a2 = {1002, "feibai01", 19};
    Object[] a3 = {1003, "feibai02", 20};

    Object[][] emps = new Object[3][];
    emps[0] = a1;
    emps[1] = a2;
    emps[2] = a3;

    Object[] emps1 = new Object[3];
    emps1[0] = a1;
    emps1[1] = a2;
    emps1[2] = a3;

  }

}
