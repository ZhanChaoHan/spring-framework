package com.jachs.spring_core.task.job2;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/***
@Scheduled所支持的参数： </br>
1. cron：cron表达式，指定任务在特定时间执行； </br>
2. fixedDelay：表示上一次任务执行完成后多久再次执行，参数类型为long，单位ms； </br>
3. fixedDelayString：与fixedDelay含义一样，只是参数类型变为String； </br>
4. fixedRate：表示按一定的频率执行任务，参数类型为long，单位ms； </br>
5. fixedRateString: 与fixedRate的含义一样，只是将参数类型变为String； </br>
6. initialDelay：表示延迟多久再第一次执行任务，参数类型为long，单位ms； </br>
7. initialDelayString：与initialDelay的含义一样，只是将参数类型变为String； </br>
8. zone：时区，默认为当前时区，一般没有用到。</br>

 * @author zhanchaohan
 *
 */
@Component
public class Job1 {
	@Scheduled(fixedDelay = 5000)
	public void run(){
        System.out.println("Ann job1....."+new Date());
    }
}
