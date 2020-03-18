package com.feibai.designpatterns.study.factorypatern.factorymethod;

import com.feibai.designpatterns.study.factorypatern.bean.Benzi;
import com.feibai.designpatterns.study.factorypatern.bean.Car;

public class BenziFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Benzi();
	}

}
