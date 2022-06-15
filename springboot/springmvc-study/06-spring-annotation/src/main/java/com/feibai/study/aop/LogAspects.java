package com.feibai.study.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspects {
  //抽取公共的切入点表达式
  @Pointcut("execution(public int com.feibai.study.aop.MathCalculator.*(..))")
  public void pointCut() {
  }

  @Before("pointCut()")
  public void logStart(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    System.out.println("" + joinPoint.getSignature().getName() + Arrays.asList(args));
  }

  @After("com.feibai.study.aop.LogAspects.pointCut()")
  public void logEnd(JoinPoint joinPoint) {
    System.out.println("" + joinPoint.getSignature().getName());
  }

  @AfterReturning(value = "pointCut()", returning = "result")
  public void logReturn(JoinPoint joinPoint, Object result) {
    System.out.println("" + joinPoint.getSignature().getName() + result);
  }

  @AfterThrowing(value = "pointCut()", throwing = "exception")
  public void logException(JoinPoint joinPoint, Exception exception) {
    System.out.println("" + joinPoint.getSignature().getName() + exception);
  }

}
