package com.feibai.study.demos.demos.jvm.classloader.demo_evernote;

/**
 * 在执行开始，先要寻找到main方法，因为main方法是程序的入口，但是在执行main方法之前，必须先加载Test类，而在
 * 加载Test类的时候发现Test类继承自Base类，因此会转去先加载Base类，在加载Base类的时候，发现有static块，便
 * 执行了static块。在Base类加载完成之后，便继续加载Test类，然后发现Test类中也有static块，便执行static块。
 * 在加载完所需的类之后，便开始执行main方法。在main方法中执行new Test()的时候会先调用父类的构造器，然后再调
 * 用自身的构造器。因此，便出现了程序的输出结果
 * 
 * @author feibai
 *
 * @Time 2019年8月13日
 *
 */
public class Test extends Base {
	static {
		System.out.println("test static");
	}

	public Test() {
		System.out.println("test constructor");
	}

	public static void main(String[] args) {
		new Test();
	}
}

class Base {
	static {
		System.out.println("base static");
	}

	public Base() {
		System.out.println("base constructor");
	}
}