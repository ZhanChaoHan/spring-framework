package com.jachs.aspectj.demo.part1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/***
 * AspectJ的五种常用通知类型：<br>
 * 
 * @Around:环绕前<br>
 * @Before:切入前执行<br>
 * @Pointcut:切入点执行<br>
 * @AfterReturning:返回前执行<br>
 * @After:切入点后执行，出现异常也会执行<br>
 * @Around:环绕后<br>
 * @author zhanchaohan
 *
 */
@Aspect
@Configuration
public class HuManAsp {
	/***
	 * 定义切入点<br>
	 * 配置com.jachs.aspectj.demo.HuMan下的Pc()<br>
	 * execution(* com.jachs.aspectj.demo.HuMan.Pc(..))<br>
	 * 配置com.jachs.aspectj.demo.HuMan下的所有方法<br>
	 * execution(* com.jachs.aspectj.demo.HuMan.*(..))<br>
	 * 配置所有包下的所有方法<br>
	 * execution(**.*(..))<br>
	 * 配置所有包下的a开头方法<br>
	 * execution(* a*(..))<br>
	 * 配置包下的所有类的所有方法<br>
	 * execution(* com.jachs.aspectj.demo.*.*(..)))<br>
	 * ———————————————— <br>
	 * execution(public *.*(..)):所有的public方法。<br>
	 * execution(* hello(..)):所有的hello()方法<br>
	 * execution(String hello(..)):所有返回值为String的hello方法。<br>
	 * execution(* hello(String)):所有参数为String类型的hello()<br>
	 * execution(* hello(String..)):至少有一个参数，且第一个参数类型为String的hello方法<br>
	 * execution(* com.aspect..*(..)):所有com.aspect包，以及子孙包下的所有方法<br>
	 * execution(* com..*.*Dao.find*(..)):com包下的所有一Dao结尾的类的一find开头的方法 <br>
	 * ————————————————
	 */
	@Pointcut("execution(* com.jachs.aspectj.demo.HuMan.*(..))")
	public void myPointCut() {
		System.out.println("myPointCut");
	}

	@After("myPointCut()")
	public void myAfter(JoinPoint joinPoint) {
		System.out.println("myAfter");
	}

	@Before("myPointCut()")
	public void myBefore() {
		System.out.println("myBefore");
	}

	// 环绕通知
	@Around("myPointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("环绕前");
		Object result = pjp.proceed();
		System.out.println("环绕后");
		return result;
	}

	// 后置 发生异常时不会执行
	@AfterReturning("myPointCut()")
	public void returning() {
		System.out.println("After returning 后置");
	}

	// 发生异常
	@AfterThrowing("myPointCut()")
	public void throwing() {
		System.out.println("发生异常了");
	}
}
