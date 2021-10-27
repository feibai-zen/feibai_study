package com.feibai.study.demos.designpatterns.builder;

/**
 * 在Director中进行指导安装
 *
 * @author feibai
 */
public class AudiAirshipDirector implements AirShipDirector {

  private AirShipBuilder builder;

  public AudiAirshipDirector(AirShipBuilder builder) {
    this.builder = builder;
  }

  @Override
  public AirShip directAirShip() {
    Engine e = builder.builderEngine();
    OrbitalModule o = builder.builderOrbitalModule();
    EscapeTower et = builder.builderEscapeTower();

    // 装配成飞船对象
    AirShip ship = new AirShip();
    ship.setEngine(e);
    ship.setEscapeTower(et);
    ship.setOrbitalModule(o);

    return ship;
  }

}
