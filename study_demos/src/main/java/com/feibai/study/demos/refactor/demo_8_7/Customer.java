package com.feibai.study.demos.refactor.demo_8_7;


import java.util.HashSet;
import java.util.Set;

/**
 * 8.7--将单向关联改为双向关联
 */
public class Customer {

  private Set _orders = new HashSet<>();


  Set friendOrders(){
    return _orders;
  }

  public Set get_orders() {
    return _orders;
  }

  public void set_orders(Set _orders) {
    this._orders = _orders;
  }


}
