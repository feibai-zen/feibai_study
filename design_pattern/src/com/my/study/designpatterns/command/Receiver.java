package com.my.study.designpatterns.command;

/**
 * 真正的命令的执行者
 * 
 * @author leeyuanlong
 *
 */
public class Receiver {
	public void action() {
		System.out.println("Receiver.action()");
	}
}
