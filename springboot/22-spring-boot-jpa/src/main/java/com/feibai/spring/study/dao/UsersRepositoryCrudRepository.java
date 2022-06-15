package com.feibai.spring.study.dao;

import com.feibai.spring.study.pojo.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository接口
 */
public interface UsersRepositoryCrudRepository extends CrudRepository<Users, Integer> {

}
