package com.feibai.study.caffeine.service;

import com.feibai.study.caffeine.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @CacheEvict(value = "TEN", key = "#id")
  public void delete(Integer id) {

  }

  @Cacheable(value = "TEN", key = "#id", sync = true)
  public User getById(Integer id) {

    System.out.println("操作数据库： id: " + id);
    return new User(id, "this is password", "userName", 19);
  }

}
