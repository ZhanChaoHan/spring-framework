package com.jachs.aspectj.part2;

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
public class HumanAspect {
	@Pointcut(value = "execution(* com.jachs.aspectj.part2.Human.*(..))")
	public void myPoint() {}
	
//	@Before(value = "myPoint()")
//	public void myBefore() {
//		System.out.println("myBefore");
//	}
//	@After(value = "myPoint()")
//	public void myAfter() {
//		System.out.println("myAfter");
//	}
	// 环绕通知
    @Around("myPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前");
        System.out.println("返回切入的类型:"+pjp.getKind());
        System.out.println("-----------------开始------------------------");
        //返回切入点参数
        Object[] arr=pjp.getArgs();
        Object result=null;
        if(arr[0]!=null) {//先判断当前执行方法为有参方法
        	boolean agr= (Boolean) arr[0];
        	if(agr) {//改参数
        		arr[0]=false;
        	}else {
        		arr[1]=true;
        	}
        	result=pjp.proceed(arr);
        }
//      result = pjp.proceed();//执行默认的
        
        System.out.println("-----------------结束------------------------");
        System.out.println("环绕后");
        return result;
    }
    // 后置 发生异常时不会执行
    @AfterReturning("myPoint()")
    public void returning() {
        System.out.println("After returning 后置");
    }
    // 发生异常
    @AfterThrowing("myPoint()")
    public void throwing() {
        System.out.println("发生异常了");
    }
}
