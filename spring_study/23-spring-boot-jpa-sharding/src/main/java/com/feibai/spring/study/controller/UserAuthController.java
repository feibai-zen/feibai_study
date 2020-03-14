package com.feibai.spring.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.feibai.spring.study.dao.UserAuthDao;
import com.feibai.spring.study.entity.UserAuthEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserAuthController {
    @Autowired
    private UserAuthDao userAuthDao;

    @PostMapping("save")
    public String save(){
        for (int i=0;i<40;i++) {
            UserAuthEntity userAuthEntity = new UserAuthEntity();
            userAuthEntity.setUserId((long)i);
            userAuthEntity.setAddDate(new Date());
            userAuthEntity.setEmail("test"+i+"@163.com");
            userAuthEntity.setPassword("123456");
            userAuthEntity.setPhone("1388888888"+i);
            Random r = new Random();
            userAuthEntity.setRemark(""+r.nextInt(100));
            userAuthDao.save(userAuthEntity);
        }
        return "success";
    }

    @PostMapping("select")
    public String select(){
        return JSONObject.toJSONString(userAuthDao.findAll(Sort.by(Sort.Order.desc("remark"))));
    }
}
