package com.feibai.designpatterns.study.command;

/**
 * 真正的命令的执行者
 * 
 * @author feibai
 *
 */
public class Receiver {
	public void action() {
		System.out.println("Receiver.action()");
	}
}
