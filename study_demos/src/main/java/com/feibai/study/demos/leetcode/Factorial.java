package com.feibai.study.demos.leetcode;

/**
 * n! 正整数的阶乘
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println(caculate_recursion(20));
        System.out.println(caculate_by_loop(20));
    }

    /**
     * 使用递归：recursion
     * 优点：代码简洁
     * <p>
     * 缺点：占用大量的系统堆栈，效率低下
     */
    private static long caculate_recursion(int n) {
        if (n == 1) {
            return 1;
        }
        return n * caculate_recursion(n - 1);
    }

    /**
     * 使用循环
     * <p>
     * 使用递归能解决的问题，使用循环都可以解决
     */
    private static long caculate_by_loop(int n) {
        long ret = 1;
        for (int i = 1; i <= n; i++) {
            ret *= i;
        }

        return ret;
    }
}
