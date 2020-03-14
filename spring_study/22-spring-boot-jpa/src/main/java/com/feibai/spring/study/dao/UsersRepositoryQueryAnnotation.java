package com.feibai.spring.study.dao;

import java.util.List;

import com.feibai.spring.study.pojo.Users;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 * Repository   @Query
 */
public interface UsersRepositoryQueryAnnotation extends Repository<Users, Integer> {

  //方法名称无所谓，是按照sql语句进行执行
  @Query("from Users where name = ?")
  List<Users> queryByNameUseHQL(String name);


  // nativeQuery默认为false,告诉hibernate value中的sql语句是标准的sql,需要进行转换。设置为true，不需要进行转换
  @Query(value = "select * from t_users where name = ?", nativeQuery = true)
  List<Users> queryByNameUseSQL(String name);

  // 如果有多个参数，那么参数按照?的顺序
  @Query("update Users set name  = ? where id  = ?")
  @Modifying
  //需要执行一个更新操作
  void updateUsersNameById(String name, Integer id);
}
