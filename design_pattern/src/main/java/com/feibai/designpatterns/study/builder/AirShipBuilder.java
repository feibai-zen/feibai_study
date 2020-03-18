package com.feibai.designpatterns.study.builder;

public interface AirShipBuilder {
	Engine builderEngine();

	OrbitalModule builderOrbitalModule();

	EscapeTower builderEscapeTower();
}
