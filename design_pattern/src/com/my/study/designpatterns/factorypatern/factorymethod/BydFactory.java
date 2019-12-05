package com.my.study.designpatterns.factorypatern.factorymethod;

import com.my.study.designpatterns.factorypatern.bean.Byd;
import com.my.study.designpatterns.factorypatern.bean.Car;

public class BydFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Byd();
	}

}
