package com.blog.web;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
	@Autowired(required=false)
	private HttpServletRequest req;

	@Around("execution(* com.blog.web.*Controller.*(..))")
	public Object before(ProceedingJoinPoint pjp){
		try {
			Object obj = pjp.proceed();
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
			return new JsonResult(e.getMessage());
			
		}
		
	}
}
