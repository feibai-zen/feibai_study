package com.my.study.designpatterns.factorypatern.factorymethod;

import com.my.study.designpatterns.factorypatern.bean.Audi;
import com.my.study.designpatterns.factorypatern.bean.Car;

public class AudiFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Audi();
	}

}
