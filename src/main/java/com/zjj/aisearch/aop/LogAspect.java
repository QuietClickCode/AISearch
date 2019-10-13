package com.zjj.aisearch.aop;

import com.zjj.aisearch.model.SystemLog;
import com.zjj.aisearch.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @program: AISearch
 * @description: 日志记录aop  感谢zheng的开源实现
 * @author: zjj
 * @create: 2019-10-13 14:53:01
 **/
@Aspect
@Slf4j
public class LogAspect {

    // 开始时间
    private long startTime = 0L;
    // 结束时间
    private long endTime = 0L;

    @Autowired
    IndexService indexServiceImpl;

    /**
         execution(表达式)
         表达式:
         [方法访问修饰符] 方法返回值 包名.类名.方法名(方法的参数)
         public * cn.itcast.spring.dao.*.*(..)
         * cn.itcast.spring.dao.*.*(..)
         * cn.itcast.spring.dao.UserDao+.*(..)
         * cn.itcast.spring.dao..*.*(..)
     * @param joinPoint
     * 应用在所有修饰符的所有名字含有controller的包的所有类的不限制返回值的所有方法
     */
    @Before("execution(* *..controller..*.*(..))")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        log.debug("doBeforeInServiceLayer");
        startTime = System.currentTimeMillis();
    }
    @After("execution(* *..controller..*.*(..))")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        log.debug("doAfterInServiceLayer");
    }

    @Around("execution(* *..controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        SystemLog systemLog = new SystemLog();
        // 从注解中获取操作名称、获取响应结果

        Object result = pjp.proceed();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        return null;
    }

}
