package com.jachs.spring_core.annotation;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * @author zhanchaohan
 *
 */
public class Monkey implements DisposableBean , InitializingBean {
    private String monkeyName = "吉吉";

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁对象Monkey");
    }
    public String toString(){
        return "MonkeyName:" + monkeyName;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化对象Monkey");
        this.monkeyName="毛毛";
    }
}