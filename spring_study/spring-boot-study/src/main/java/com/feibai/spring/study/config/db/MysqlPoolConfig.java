package com.feibai.spring.study.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;

import java.sql.SQLException;

/**
 * spring-boot-data-packing 设置Mysql多实例的基类
 */
public class MysqlPoolConfig {

  @Value("${jdbc.driver}")
  private String driverClassName;
  @Value("${jdbc.maxActive}")
  private int maxActive;
  @Value("${jdbc.minIdle}")
  private int minIdle;
  @Value("${jdbc.initialSize}")
  private int initialSize;
  @Value("${jdbc.maxWait}")
  private int maxWait;
  @Value("${jdbc.timeBetweenEvictionRunsMillis}")
  private long timeBetweenEvictionRunsMillis;
  @Value("${jdbc.minEvictableIdleTimeMillis}")
  private long minEvictableIdleTimeMillis;
  @Value("${jdbc.testWhileIdle}")
  private boolean testWhileIdle;
  @Value("${jdbc.testOnBorrow}")
  private boolean testOnBorrow;
  @Value("${jdbc.testOnReturn}")
  private boolean testOnReturn;
  @Value("${jdbc.poolPreparedStatements}")
  private boolean poolPreparedStatements;
  @Value("${jdbc.maxOpenPreparedStatements}")
  private int maxOpenPreparedStatements;
  @Value("${jdbc.validationQuery}")
  private String validationQuery;

  public DruidDataSource createDataSource(String url,
                                          String username, String password) throws SQLException {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setDriverClassName(driverClassName);
    druidDataSource.setUrl(url);
    druidDataSource.setUsername(username);
    druidDataSource.setPassword(password);
    druidDataSource.setInitialSize(initialSize);
    druidDataSource.setMinIdle(minIdle);
    druidDataSource.setMaxActive(maxActive);
    druidDataSource.setMaxWait(maxWait);
    druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    druidDataSource.setValidationQuery(validationQuery);
    druidDataSource.setTestWhileIdle(testWhileIdle);
    druidDataSource.setTestOnBorrow(testOnBorrow);
    druidDataSource.setTestOnReturn(testOnReturn);
    druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
    druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxOpenPreparedStatements);
    // Failed to obtain JDBC Connection; 解决办法
    druidDataSource.setFilters("stat,wall,log4j");
    druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return druidDataSource;
  }


}
