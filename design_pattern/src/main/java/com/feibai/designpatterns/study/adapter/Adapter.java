package com.feibai.designpatterns.study.adapter;

/**
 * 适配器 (类适配器方式) (相当于usb和ps/2的转接器)
 * 
 * @author feibai
 *
 *
 */
public class Adapter extends Adaptee implements Target {

	@Override
	public void translateData() {
		super.communicate();
	}

}

//适配器 (对象适配器方式,使用了组合的方式跟被适配对象整合) (相当于usb和ps/2的转接器)
class Adapter2 implements Target {

	private Adaptee adaptee;

	@Override
	public void translateData() {
		adaptee.communicate();
	}

	public Adapter2(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

}