package com.bbj.base.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class TimeHandler {
	
//	@Before("execution(* com.bbj.base.service.MenuService.*(..))")
	public void printTime() {
		System.out.println("CurrentTime = " + System.currentTimeMillis());
	}
}
