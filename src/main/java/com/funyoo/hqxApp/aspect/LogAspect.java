package com.funyoo.hqxApp.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;

/**
 * 日志
 *
 * @author funyoo
 * @creatDate 2019/12/02 15:00
 */
@Aspect
@Component
public class LogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private Logger logger = LogManager.getLogger(LogAspect.class);

    //com.kzj.kzj_rabbitmq.controller 包中所有的类的所有方法切面
    //@Pointcut("execution(public * com.kzj.kzj_rabbitmq.controller.*.*(..))")

    //只针对 MessageController 类切面
    //@Pointcut("execution(public * com.kzj.kzj_rabbitmq.controller.MessageController.*(..))")

    //统一切点,对com.funyoo.hqxApp.controller及其子包中所有的类的所有方法切面
    @Pointcut("execution(public * com.funyoo.hqxApp.controller..*.*(..))")
    public void Pointcut() {
    }

    //前置通知
    @Before("Pointcut()")
    public void beforeMethod(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String ip = request.getRemoteAddr();
            String url = request.getRequestURL().toString();
            String type = request.getMethod();
            String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            String paras = Arrays.toString(joinPoint.getArgs());

            logger.info("[" + ip + "] [" + url + "] [" + type + "] [" + method + "] " + paras);
        }
    }

    //@After: 后置通知
//    @After("Pointcut()")
//    public void afterMethod(JoinPoint joinPoint){
//        log.info("调用了后置通知");
//    }

    //@AfterRunning: 返回通知 rsult为返回内容
    @AfterReturning(value="Pointcut()",returning="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        logger.info("[" + result + "] [" + (System.currentTimeMillis() - startTime.get()) + "ms]");
    }

    //@AfterThrowing: 异常通知
    @AfterThrowing(value="Pointcut()",throwing="e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e){
        logger.error(e.getMessage());
        logger.error(e.getLocalizedMessage());
    }

    //@Around：环绕通知 环绕位于前置拦截前和后置返回前执行
//    @Around("Pointcut()")
//    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("开始环绕");
//        long startTime = System.currentTimeMillis();
//
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        String ip = request.getRemoteAddr();
//        String url = request.getRequestURL().toString();
//        String type = request.getMethod();
//        String method = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
//        String paras = Arrays.toString(pjp.getArgs());
//
//        Object object = pjp.proceed();
//
//        String result = object.toString();
//        long time = System.currentTimeMillis() - startTime;
//        logger.info("[" + ip + "] [" + url + "] [" + type + "] [" + method + "] " + paras + " [" + result + "]");
//
//        System.out.println("环绕结束");
//        return object;
//    }

}
