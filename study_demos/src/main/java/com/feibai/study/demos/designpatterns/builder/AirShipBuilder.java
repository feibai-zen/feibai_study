package com.feibai.study.demos.designpatterns.builder;

public interface AirShipBuilder {
  Engine builderEngine();

  OrbitalModule builderOrbitalModule();

  EscapeTower builderEscapeTower();
}
