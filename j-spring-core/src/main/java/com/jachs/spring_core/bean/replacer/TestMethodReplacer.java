package com.jachs.spring_core.bean.replacer;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/****
 * @author  zhanchaohan
 */
public class TestMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("修改老方法");
        return null;
    }
}
