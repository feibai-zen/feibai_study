package com.my.study.demos.multithread.foundation.threadcreator;

/**
 * 1.采用实现Runnable、Callable接口的方式创建多线程的优缺点：
 * 
 * 优势： （1）线程类只是实现了Runnable接口与Callable接口，还可以继承其他类。
 * （2）在这种方式下，多个线程可以共享一个target对象，所以非常适合多个相同线程来处理同一份资源的情况，从而可以
 * 将CPU、代码和数据分开，形成清晰的模型，较好地体现了面向对象的思想。
 * 劣势：编程稍稍复杂，如果需要访问当前线程，则必须使用Thread.currentThread()方法。
 * 
 * 2.采用继承Thread类的方法创建多线程的优缺点： 劣势：因为线程类已经继承了Thread类，所以不能再继承其他父类。
 * 优势：编写简单，如果需要访问当前线程，则无须使用Thread.currentThread()方法，直接使用this即可获得当前线程。
 * 
 * @author liyuanlong
 *
 */
public class ThreadTest {

	public static void main(String[] args) {
//		Thread t1 = new ThreadExtends("t1");
//		Thread t2 = new ThreadExtends("t2");
//		t1.start();
//		t2.start();

		Runnable runnable = new ThreadRunnable();
		Thread t3 = new Thread(runnable, "t3");
		Thread t4 = new Thread(runnable, "t4");
		t3.start();
		t4.start();
	}

}
