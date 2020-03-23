package com.feibai.designpatterns.study.observer.observer3;

import java.util.Objects;

/**
 * 对于用户的抽象
 */
public class User implements MyObserver {
  private String mName;

  public User(String name) {
    mName = name;
  }

  @Override
  public void receive(NewsModel model) {
    System.out.println(mName + " receive news:" + model.getTitle() + "  " + model.getContent());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(mName, user.mName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mName);
  }
}