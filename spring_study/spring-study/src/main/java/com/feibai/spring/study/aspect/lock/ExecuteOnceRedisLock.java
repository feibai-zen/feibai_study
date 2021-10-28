package com.feibai.spring.study.aspect.lock;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExecuteOnceRedisLock {
  /**
   * redis key
   *
   * @return
   */
  String key();

  /**
   * 过期时间 默认30秒 -1永不过期
   *
   * @return
   */
  long timeout() default 30000;

  /**
   * 执行完成后是否直接删除 默认不删除等待过期
   *
   * @return
   */
  boolean del() default false;
}
