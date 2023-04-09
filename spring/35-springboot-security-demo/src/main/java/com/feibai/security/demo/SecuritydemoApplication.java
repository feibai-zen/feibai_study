package com.feibai.security.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.util.StringUtils;

@SpringBootApplication
@MapperScan("com.feibai.security.demo.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecuritydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritydemoApplication.class, args);
    }

}
