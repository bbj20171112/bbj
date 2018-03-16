package com.bbj.base.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeHandlerAnnotation {
	
	@Pointcut("execution(* com.bbj.base.service.MenuService.*(..))")//切入点
    public  void testPointCut(){

    }
    @Before("testPointCut()")
    public void begin(JoinPoint join)
    {
        System.out.println("日志前");
    }//关注点代码

    @After("testPointCut()")
    public void end()
    {
        System.out.println("日志后");
    }//关注点代码
}
