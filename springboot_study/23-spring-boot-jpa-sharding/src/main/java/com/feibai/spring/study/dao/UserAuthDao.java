package com.feibai.spring.study.dao;

import com.feibai.spring.study.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAuthDao extends JpaRepository<UserAuthEntity, Long> {
}
