package com.feibai.spring.study.aspect.lock;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@Slf4j
public class ExecuteOnceRedisLockInterceptor {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Around("@annotation(com.feibai.spring.study.aspect.lock.ExecuteOnceRedisLock)")
  public Object around(ProceedingJoinPoint pjp) {
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    ExecuteOnceRedisLock executeOnceRedisLock = methodSignature.getMethod().getAnnotation(ExecuteOnceRedisLock.class);
    Boolean success;
    if (executeOnceRedisLock.timeout() == -1) {
      success = stringRedisTemplate.opsForValue().setIfAbsent(executeOnceRedisLock.key(), "");
    } else {
      success = stringRedisTemplate.opsForValue()
              .setIfAbsent(executeOnceRedisLock.key(), "", executeOnceRedisLock.timeout(), TimeUnit.MILLISECONDS);
    }
    Object res = null;
    try {
      if (success != null && success) {
        res = pjp.proceed();
        if (executeOnceRedisLock.del()) {
          stringRedisTemplate.opsForValue().getOperations().delete(executeOnceRedisLock.key());
        }
      }
    } catch (Throwable throwable) {
      log.error("定时任务执行出错，错误信息为", throwable);
    }
    return res;
  }
}
