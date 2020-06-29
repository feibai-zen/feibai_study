package com.feibai.study.demos.refactor.demo_8_7;


/**
 * 8.7--将单向关联改为双向关联
 * 反向指针
 *
 * 由哪个类控制关联关系
 * 1) 如果两者都是引用对象，而其间的关联是"一对多"关系，那么就由"拥有单一引用"的那一方承担"控制着"角色
 *
 */
public class Order {

  private Customer _customer;

  public Customer get_customer() {
    return _customer;
  }

  public void set_customer(Customer _customer) {
    this._customer = _customer;
  }

  void setCustomer(Customer arg) {

    if (_customer != null) {
      _customer.friendOrders().remove(this);
      _customer = arg;
    }
    if (_customer != null) {
      _customer.friendOrders().add(this);
    }

  }
}
