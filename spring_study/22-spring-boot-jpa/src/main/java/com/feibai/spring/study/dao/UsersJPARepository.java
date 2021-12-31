package com.feibai.spring.study.dao;

import com.feibai.spring.study.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 参数一 T :当前需要映射的实体
 * 参数二 ID :当前映射的实体中的OID的类型
 * <p>
 * 该接口继承了PagingAndSortingRepository接口。该接口的特点是对继承的父接口的返回值做适配。
 */
public interface UsersJPARepository extends JpaRepository<Users, Integer> {

}
