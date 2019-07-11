package com.bjsxt.service;

import java.util.List;

import com.bjsxt.pojo.Users;

public interface UsersService {
	
	void addUser(Users users);
	List<Users> findUserAll();
	Users findUserById(Integer id);
	void updateUser(Users users);
	void deleteUserById(Integer id);
} 
