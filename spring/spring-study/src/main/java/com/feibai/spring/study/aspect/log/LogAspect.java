package com.feibai.spring.study.aspect.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

  private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);


  /**
   * 切入点表达式： execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?) 抽取可重用的切入点表达式;
   * 定义一个可重用的切入点表达式，以后的表达式直接引用;
   * 环绕在单切面环境： LOG===环绕前置 LOG===前置 目标方法 LOG===环绕返回 LOG===环绕后置 LOG===后置 LOG===返回;
   * 拦截包或者子包中定义的方法;
   */
  @Pointcut("within(com.feibai.spring.study.service..*) && @annotation(com.feibai.spring.study.aspect.log.AuditLog)")
  public void logPoint() {
  }

  /**
   * 前置通知
   *
   * @Before：前置通知；在目标方法运行之前运行；
   */
  @Before("logPoint()")
  public static void logStart(JoinPoint joinPoint) {
    LogObject logObject = getLogObject(joinPoint);
    Object[] args = joinPoint.getArgs();
    LOG.info("【AuditLog-{}-{}】方法名：{} 运行开始 . params: {}", logObject.getBusinessStr(),
        logObject.getOpsId(), logObject.getMethodName(),
        Arrays.asList(args));
  }


  /**
   * @AfterThrowing：异常通知：在目标方法出现异常之后运行
   */
  @AfterThrowing(value = "logPoint()", throwing = "e")
  public static void logException(JoinPoint joinPoint, Exception e) {
    LogObject logObject = getLogObject(joinPoint);
    Object[] args = joinPoint.getArgs();
    LOG.info("【AuditLog-{}-{}】方法名：{} 发生异常 . params: {}", logObject.getBusinessStr(),
        logObject.getOpsId(), logObject.getMethodName(),
        Arrays.asList(args), e);
  }

  /**
   * @After：后置通知：在目标方法最终结束的时候运行；
   */
  @After("logPoint()")
  public void logEnd(JoinPoint joinPoint) {
    LogObject logObject = getLogObject(joinPoint);
    Object[] args = joinPoint.getArgs();
    LOG.info("【AuditLog-{}-{}】方法名：{} 运行结束 . params: {}", logObject.getBusinessStr(),
        logObject.getOpsId(), logObject.getMethodName(),
        Arrays.asList(args));
  }


  /**
   * 从包名中获取业务
   */
  private static String getBusinessStr(String packageStr) {
    String[] strs = packageStr.split(" ");
    String methodPackage = strs[1];
    String[] packagePaths = methodPackage.split("\\.");
    return packagePaths[packagePaths.length - 4].toUpperCase();
  }

  /**
   * 从切点获取日志元素 核心约定大于配置 当前约定
   * 1.1 包名结构、按目前结构来组织
   */
  private static LogObject getLogObject(JoinPoint joinPoint) {
    LogObject logObject = new LogObject();
    //1.获取opsId
    logObject.setOpsId(String.valueOf(0));
    //2.获取方法签名；当前方法的所有详细信息都在
    Signature signature = joinPoint.getSignature();
    logObject.setMethodName(signature.getName());
    //获取业务名称
    logObject.setBusinessStr(getBusinessStr(signature.toString()));
    return logObject;
  }
}