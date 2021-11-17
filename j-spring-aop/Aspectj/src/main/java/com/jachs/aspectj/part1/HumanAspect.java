package com.jachs.aspectj.part1;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/***
 * 
 * @author zhanchaohan
 *
 */
@Aspect
public class HumanAspect {
	
	@Before(value = "execution(* com.jachs.aspectj.part1.Human.*(..))")
	public void wakeUp() {
		System.out.println("睡醒了");
	}
	@After(value = "execution(* com.jachs.aspectj.part1.Human.*(..))")
	public void sleep() {
		System.out.println("开始睡觉");
	}
}
