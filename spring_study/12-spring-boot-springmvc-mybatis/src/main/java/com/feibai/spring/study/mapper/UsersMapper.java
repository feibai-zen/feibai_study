package com.feibai.spring.study.mapper;

import java.util.List;

import com.feibai.spring.study.pojo.Users;

public interface UsersMapper {

  void insertUser(Users users);

  List<Users> selectUsersAll();

  Users selectUsersById(Integer id);

  void updateUser(Users users);

  void deleteUserById(Integer id);
}
