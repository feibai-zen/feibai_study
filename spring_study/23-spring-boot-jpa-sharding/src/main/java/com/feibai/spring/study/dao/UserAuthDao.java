package com.feibai.spring.study.dao;

import com.feibai.spring.study.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mazhq
 * @Title: UserAuthDao
 * @ProjectName: zeus
 * @Description: TODO
 * @date 2019/7/30 16:43
 */
@Repository
public interface UserAuthDao extends JpaRepository<UserAuthEntity, Long> {
}
