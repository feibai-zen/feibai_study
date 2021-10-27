package com.feibai.study.demos.designpatterns.factorypatern;

import com.feibai.study.demos.designpatterns.factorypatern.bean.Audi;
import com.feibai.study.demos.designpatterns.factorypatern.bean.Byd;
import com.feibai.study.demos.designpatterns.factorypatern.bean.Car;

/**
 * 简单工厂模式
 * <p>
 * 特点：通过接收的参数的不同来返回不同的实例对象。对于新增加产品不修改代码无法进行拓展。
 *
 * @author feibai
 */
public class FactorySimple {

  // 方式1
  public static Car createCar(String type) {
    if (type.equals("Audi")) {
      return new Audi();
    } else if (type.equals("Byd")) {
      return new Byd();
    }

    return null;
  }

  // 方式2
  public static Car creatAudi() {
    return new Audi();
  }

  public static Car creatByd() {
    return new Byd();
  }

}
