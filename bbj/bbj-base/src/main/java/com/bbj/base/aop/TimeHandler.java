package com.bbj.base.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeHandler {
	
	@Before("execution(* com.bbj.base.service.MenuService.*(..))")
	public void printTime() {
		System.out.println("CurrentTime = " + System.currentTimeMillis());
	}
	
	@Pointcut("execution(* com.bbj.base.service.MenuService.*(..))")//切入点
    public  void testPointCut(){

    }
    @Before("testPointCut()")
    public void begin()
    {
        System.out.println("事务开启123");
    }//关注点代码

    @After("testPointCut()")
    public void end()
    {
        System.out.println("事务结束456");
    }//关注点代码
}
