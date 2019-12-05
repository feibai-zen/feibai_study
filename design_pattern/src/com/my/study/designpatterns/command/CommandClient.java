package com.my.study.designpatterns.command;

public class CommandClient {
	public static void main(String[] args) {
		Command c = new ConcreteCommand(new Receiver());
		Invoke i = new Invoke(c);
		i.call();

//		new Receiver().action();

	}
}
