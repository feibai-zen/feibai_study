package com.feibai.study.demos.designpatterns.factorypatern.factoryabstract;

public interface Tyre {
	void revolve();
}

class LuxuryTyre implements Tyre {

	@Override
	public void revolve() {
		System.out.println("旋转不磨损！");
	}

}

class LowTyre implements Tyre {

	@Override
	public void revolve() {
		System.out.println("旋转磨损快！");
	}

}
