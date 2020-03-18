package com.feibai.designpatterns.study.factorypatern.factoryabstract;

public interface CarFactory {
	Engine createEngine();

	Seat createSeat();

	Tyre createTyre();
}
