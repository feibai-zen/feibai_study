package com.my.study.designpatterns.factorypatern.factorymethod;

import com.my.study.designpatterns.factorypatern.bean.Benzi;
import com.my.study.designpatterns.factorypatern.bean.Car;

public class BenziFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Benzi();
	}

}
