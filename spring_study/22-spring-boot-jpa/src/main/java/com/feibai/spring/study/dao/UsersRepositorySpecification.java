package com.feibai.spring.study.dao;

import com.feibai.spring.study.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaSpecificationExecutor
 * <p>
 * 1）能够按条件筛选后进行分页
 * 2）
 * <p>
 * springboot这个接口不单独使用，会报错。会配合其他接口使用
 */
public interface UsersRepositorySpecification extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

}
