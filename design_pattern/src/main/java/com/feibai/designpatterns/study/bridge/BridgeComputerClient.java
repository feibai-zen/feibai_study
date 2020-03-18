package com.feibai.designpatterns.study.bridge;

public class BridgeComputerClient {
	public static void main(String[] args) {
		// 销售联想的笔记本电脑
		BridgeComputer c = new Laptop2(new Lenovo());
		c.sale();

		// 销售神舟的台式机
		BridgeComputer c2 = new Desktop2(new Shenzhou());
		c2.sale();

	}
}
