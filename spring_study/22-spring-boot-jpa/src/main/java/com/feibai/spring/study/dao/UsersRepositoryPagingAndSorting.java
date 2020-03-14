package com.feibai.spring.study.dao;

import com.feibai.spring.study.pojo.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * PagingAndSortingRepository接口---提供了分页和排序的操作。该接口继承了CrudRepository接口
 */
public interface UsersRepositoryPagingAndSorting extends PagingAndSortingRepository<Users, Integer> {

}
