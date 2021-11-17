package com.jachs.aspectj.part3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/***
 * 
 * @author zhanchaohan
 *
 */
@Aspect
public class GuestAspect {
	Shopkeeper chef=Shopkeeper.initChef("皮皮虾");
	
	//只切buy开头的方法
	@Pointcut(value = "execution(* com.jachs.aspectj.part3.Guest.buy*(..))")
	public void guestPoint() {}

	
	@Around("guestPoint()")
	public Object guestAround(ProceedingJoinPoint pjp) throws Throwable {
		chef.sellGoods(pjp.getSignature().getName());
		System.out.println("-------------------------------");
		Object result = pjp.proceed();// 执行默认的
		System.out.println("-------------------------------");
		chef.collection(50);
		
		return result;
	}

	@AfterReturning("guestPoint()")
	public void returning() {
		System.out.println("After returning 后置");
	}

	@AfterThrowing("guestPoint()")
	public void throwing() {
		System.out.println("发生异常了");
	}
}
