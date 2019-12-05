package com.feibai.study.demos.designpatterns.factorypatern.factorymethod;

import com.feibai.study.demos.designpatterns.factorypatern.bean.Byd;
import com.feibai.study.demos.designpatterns.factorypatern.bean.Car;

public class BydFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Byd();
	}

}
