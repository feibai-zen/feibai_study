package com.feibai.spring.study.config.db;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@MapperScan(basePackages = {
        "com.springboot.study.dao"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class DBConfig1 extends MysqlPoolConfig {

  @Value("${jdbc.url1}")
  private String url;
  @Value("${jdbc.username1}")
  private String username;
  @Value("${jdbc.password1}")
  private String password;

  /**
   * 配置redis连接工厂
   */
  @Bean(name = "dataSource1")
  public DruidDataSource DbConnectionFactory2() throws SQLException {
    DruidDataSource druidDataSource = createDataSource(url, username, password);
    druidDataSource.clearFilters();
    List<Filter> filterList = new ArrayList<>();
    filterList.add(wallFilter());
    druidDataSource.setProxyFilters(filterList);
    return druidDataSource;
  }

  @Bean(name = "sqlSessionFactory1")
  public SqlSessionFactory sqlSessionFactory1(
          @Qualifier("dataSource1") DruidDataSource DataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(DataSource);
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sqlSessionFactoryBean
            .setMapperLocations(resolver.getResources("classpath:mybatis/study1/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "transactionManager1")
  public PlatformTransactionManager transactionManager1(
          @Qualifier("dataSource1") DruidDataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public WallFilter wallFilter() {
    WallFilter wallFilter = new WallFilter();
    wallFilter.setConfig(wallConfig());

    return wallFilter;
  }

  @Bean
  public WallConfig wallConfig() {
    WallConfig config = new WallConfig();
    config.setMultiStatementAllow(true);
    config.setNoneBaseStatementAllow(true);
    return config;
  }

}

