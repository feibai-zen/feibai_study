package com.feibai.designpatterns.study.builder;

/**
 * 在Builder中进行零件生产，可以结合工厂模式、单例模式创建零件
 *
 * @author feibai
 */
public class AudiAirShipBuilder implements AirShipBuilder {
  // StringBuilder, 以后学习XML解析中，JDOM库中的类：DomBuilder,SaxBuilder
  @Override
  public Engine builderEngine() {
    System.out.println("构建Audi牌发动机！");
    return new Engine("Audi牌发动机！");
  }

  @Override
  public EscapeTower builderEscapeTower() {

    System.out.println("构建逃逸塔");
    return new EscapeTower("Audi牌逃逸塔");
  }

  @Override
  public OrbitalModule builderOrbitalModule() {
    System.out.println("构建轨道舱");
    return new OrbitalModule("Audi牌轨道舱");
  }

}
