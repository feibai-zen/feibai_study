package com.feibai.study.demos.designpatterns.factorypatern;

import com.feibai.study.demos.designpatterns.factorypatern.factoryabstract.CarFactory;
import com.feibai.study.demos.designpatterns.factorypatern.factoryabstract.Engine;
import com.feibai.study.demos.designpatterns.factorypatern.factoryabstract.LuxuryCarFactory;

public class FactoryAbstrastClient {
	public static void main(String[] args) {
		CarFactory factory = new LuxuryCarFactory();
		Engine e = factory.createEngine();
		e.run();
		e.start();

	}
}
