package com.feibai.designpatterns.study.factorypatern;

import com.feibai.designpatterns.study.factorypatern.factoryabstract.CarFactory;
import com.feibai.designpatterns.study.factorypatern.factoryabstract.Engine;
import com.feibai.designpatterns.study.factorypatern.factoryabstract.LuxuryCarFactory;

public class FactoryAbstrastClient {
	public static void main(String[] args) {
		CarFactory factory = new LuxuryCarFactory();
		Engine e = factory.createEngine();
		e.run();
		e.start();

	}
}
