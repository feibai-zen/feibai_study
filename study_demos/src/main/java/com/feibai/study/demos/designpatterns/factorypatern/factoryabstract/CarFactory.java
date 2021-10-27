package com.feibai.study.demos.designpatterns.factorypatern.factoryabstract;

public interface CarFactory {
	Engine createEngine();

	Seat createSeat();

	Tyre createTyre();
}
