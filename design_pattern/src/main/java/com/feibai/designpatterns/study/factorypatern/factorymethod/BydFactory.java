package com.feibai.designpatterns.study.factorypatern.factorymethod;

import com.feibai.designpatterns.study.factorypatern.bean.Byd;
import com.feibai.designpatterns.study.factorypatern.bean.Car;

public class BydFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Byd();
	}

}
