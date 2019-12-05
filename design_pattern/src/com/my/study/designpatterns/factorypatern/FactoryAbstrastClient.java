package com.my.study.designpatterns.factorypatern;

import com.my.study.designpatterns.factorypatern.factoryabstract.CarFactory;
import com.my.study.designpatterns.factorypatern.factoryabstract.Engine;
import com.my.study.designpatterns.factorypatern.factoryabstract.LuxuryCarFactory;

public class FactoryAbstrastClient {
	public static void main(String[] args) {
		CarFactory factory = new LuxuryCarFactory();
		Engine e = factory.createEngine();
		e.run();
		e.start();

	}
}
