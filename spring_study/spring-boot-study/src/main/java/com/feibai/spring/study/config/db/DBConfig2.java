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
        "com.spring.boot.study.dao"}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class DBConfig2 extends MysqlPoolConfig {

  @Value("${jdbc.url2}")
  private String url;
  @Value("${jdbc.username2}")
  private String username;
  @Value("${jdbc.password2}")
  private String password;

  /**
   * 配置mysql连接工厂
   */
  @Bean(name = "dataSource2")
  public DruidDataSource connectionFactory2() throws SQLException {
    DruidDataSource druidDataSource = createDataSource(url, username, password);
    druidDataSource.clearFilters();
    List<Filter> filterList = new ArrayList<>();
    filterList.add(wallFilter());
    druidDataSource.setProxyFilters(filterList);
    return druidDataSource;
  }


  @Bean(name = "sqlSessionFactory2")
  public SqlSessionFactory sqlSessionFactory2(
          @Qualifier("dataSource2") DruidDataSource dataSource) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/study2/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "transactionManager2")
  public PlatformTransactionManager transactionManager2(@Qualifier("dataSource2") DruidDataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  private WallFilter wallFilter() {
    WallFilter wallFilter = new WallFilter();
    wallFilter.setConfig(wallConfig());

    return wallFilter;
  }

  private WallConfig wallConfig() {
    WallConfig config = new WallConfig();
    config.setMultiStatementAllow(true);
    config.setNoneBaseStatementAllow(true);
    return config;
  }

}
