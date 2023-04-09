package com.feibai.study.caffeine.controller;

import com.feibai.study.caffeine.model.User;
import com.feibai.study.caffeine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @Autowired
  private UserService userService;

  @GetMapping("/caffeine/get")
  public User getByUserId(Integer id) {
    return userService.getById(id);
  }

  @GetMapping("/caffeine/delete")
  public void deleteUser(Integer id) {
    userService.delete(id);
  }
}
