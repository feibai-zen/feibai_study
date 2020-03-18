package com.feibai.designpatterns.study.factorypatern.factorymethod;

import com.feibai.designpatterns.study.factorypatern.bean.Audi;
import com.feibai.designpatterns.study.factorypatern.bean.Car;

public class AudiFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Audi();
	}

}
