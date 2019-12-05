package com.my.study.demos.foundation.string;

public class FinalConstPool {

	/**
	 * 采用字面值的方式赋值
	 */
	public void test01() {
		String str1 = "aaa";
		String str2 = "aaa";
		System.out.println("===========test1============");
		System.out.println(str1 == str2);// true 可以看出str1跟str2是指向同一个对象，指向相同的地址
	}
	
	
	
	/**
	 * 采用new关键字新建一个字符串对象
	 */
	public void test02() {
		String str3=new String("aaa");
		String str4=new String("aaa");
		System.out.println("===========test2============");
		System.out.println(str3==str4);//false 可以看出用new的方式是生成不同的对象
		
	}

	/**
	 * 编译器确定
	 */
	public void test03() {
		String s0 = "helloworld";
		String s1 = "helloworld";
		String s2 = "hello" + "world";
		System.out.println("===========test3============");
		System.out.println(s0 == s1); // true 可以看出s0跟s1是指向同一个对象
		System.out.println(s0 == s2); // true 可以看出s0跟s2是指向同一个对象
	}

	/**
	 * 编译期无法确定
	 */
	public void test4() {
		String s0 = "helloworld";
		String s1 = new String("helloworld");
		String s2 = "hello" + new String("world");
		System.out.println("===========test4============");
		System.out.println(s0 == s1); // false
		System.out.println(s0 == s2); // false
		System.out.println(s1 == s2); // false
	}

	/**
	 * 继续-编译期无法确定
	 */
	public void test5() {
		String str1 = "abc";
		String str2 = "def";
		String str3 = str1 + str2;
		System.out.println("===========test5============");
		System.out.println(str3 == "abcdef"); // false
	}

	/**
	 * 编译期优化
	 */
	public void test06() {
		String s0 = "a1";
		String s1 = "a" + 1;
		System.out.println("===========test6============");
		System.out.println((s0 == s1)); // result = true
		String s2 = "atrue";
		String s3 = "a" + "true";
		System.out.println((s2 == s3)); // result = true
		String s4 = "a3.4";
		String s5 = "a" + 3.4;
		System.out.println((s4 == s5)); // result = true
	}

	/**
	 * 编译期无法确定
	 */
	public void test7() {
		String s0 = "ab";
		String s1 = "b";
		String s2 = "a" + s1;
		System.out.println("===========test7============");
		System.out.println((s0 == s2)); // result = false
	}

	/**
	 * 比较字符串常量的“+”和字符串引用的“+”的区别
	 */
	public void test8() {
		String test = "javalanguagespecification";
		String str = "java";
		String str1 = "language";
		String str2 = "specification";
		System.out.println("===========test8============");
		System.out.println(test == "java" + "language" + "specification");
		System.out.println(test == str + str1 + str2);
	}

	/**
	 * 编译期确定
	 */
	public void test9() {
		String s0 = "ab";
		final String s1 = "b";
		String s2 = "a" + s1;
		System.out.println("===========test9============");
		System.out.println((s0 == s2)); // result = true
	}

	/**
	 * 
	 * 编译期无法确定
	 */
	public void test10() {
		String s0 = "ab";
		final String s1 = getS1();
		String s2 = "a" + s1;
		System.out.println("===========test10============");
		System.out.println((s0 == s2)); // result = false
	}

	private static String getS1() {
		return "b";
	}

	/**
	 * 关于String.intern()
	 */
	public void test11() {
		String s0 = "kvill";
		String s1 = new String("kvill");
		String s2 = new String("kvill");
		System.out.println("===========test11============");
		System.out.println(s0 == s1); // false
		System.out.println("**********");
		s1.intern(); // 虽然执行了s1.intern(),但它的返回值没有赋给s1
		s2 = s2.intern(); // 把常量池中"kvill"的引用赋给s2
		System.out.println(s0 == s1); // flase
		System.out.println(s0 == s1.intern()); // true//说明s1.intern()返回的是常量池中"kvill"的引用
		System.out.println(s0 == s2); // true
	}

	/**
	 * 关于equals和==
	 */
	public void test12() {
		String s1 = "hello ";
		String s2 = "hello";
		String s3 = new String("hello");
		System.out.println("===========test12============");
		System.out.println(s1 == s2); // true,表示s1和s2指向同一对象，它们都指向常量池中的"hello"对象
		// flase,表示s1和s3的地址不同，即它们分别指向的是不同的对象,s1指向常量池中的地址，s3指向堆中的地址
		System.out.println(s1 == s3);
		System.out.println(s1.equals(s3)); // true,表示s1和s3所指向对象的内容相同
	}
}
