package com.feibai.designpatterns.study.bridge;

/**
 * 电脑类型的维度
 * 
 * @author feibai
 *
 */
public class BridgeComputer {

	protected Brand brand;

	public BridgeComputer(Brand b) {
		this.brand = b;
	}

	public void sale() {
		brand.sale();
	}

}

class Desktop2 extends BridgeComputer {

	public Desktop2(Brand b) {
		super(b);
	}

	@Override
	public void sale() {
		super.sale();
		System.out.println("销售台式机");
	}
}

class Laptop2 extends BridgeComputer {

	public Laptop2(Brand b) {
		super(b);
	}

	@Override
	public void sale() {
		super.sale();
		System.out.println("销售笔记本");
	}
}

interface Brand {
	void sale();
}

class Lenovo implements Brand {

	@Override
	public void sale() {
		System.out.println("销售联想电脑");
	}

}

class Dell implements Brand {

	@Override
	public void sale() {
		System.out.println("销售Dell电脑");
	}

}

class Shenzhou implements Brand {

	@Override
	public void sale() {
		System.out.println("销售神舟电脑");
	}

}