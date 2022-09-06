package com.jachs.spring_core.util;

import org.springframework.util.StopWatch;

/***
 * 
 * @author zhanchaohan
 *
 */
public class StopWatchDemo {
	public static void main(String[] args) throws InterruptedException {
		StopWatch stopWatch = new StopWatch("测试秒表");
		
		stopWatch.start("p1");
		m1();
		stopWatch.stop();
		
		stopWatch.start("p2");
		m2();
		stopWatch.stop();
		
		stopWatch.start("p3");
		m3();
		stopWatch.stop();
		
		System.out.println(stopWatch.isRunning());
        System.out.println(stopWatch.prettyPrint());//打印详细信息
        System.out.println(stopWatch.shortSummary());//打印简要信息
	}
	
	public static void  m1() throws InterruptedException {
		Thread.sleep(1000);
		
		System.out.println("执行方法1");
	}
	
	public static void  m2() throws InterruptedException {
		Thread.sleep(2000);
		
		System.out.println("执行方法2");
	}
	
	public static void  m3() throws InterruptedException {
		Thread.sleep(3000);
		
		System.out.println("执行方法3");
	}
}
