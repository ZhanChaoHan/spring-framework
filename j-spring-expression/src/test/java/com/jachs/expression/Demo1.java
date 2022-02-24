package com.jachs.expression;

import com.jachs.expression.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.expression.BeanExpressionContextAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Collections;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Demo1 {
	//简单使用
	@Test
	public void test1() {
		//创建表达式解析器
		ExpressionParser expressionParser = new SpelExpressionParser();
		//解析表达式并获取结果
		System.out.println(expressionParser.parseExpression("'hello'").getValue());
		System.out.println(expressionParser.parseExpression("123").getValue());
		System.out.println(expressionParser.parseExpression("12.34").getValue());
		System.out.println(expressionParser.parseExpression("10e2").getValue());
		System.out.println(expressionParser.parseExpression("true").getValue());
		System.out.println(expressionParser.parseExpression("new java.util.Date()").getValue());
	}
    //变量引用
    @Test
    public void test2(){
        //创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        //创建数据上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        //设置变量
        evaluationContext.setVariable("a", 12);
        evaluationContext.setVariable("b", 34);
        evaluationContext.setVariable("c", 56);
        //解析表达式
        System.out.println(expressionParser.parseExpression("#a+#b-#c").getValue(evaluationContext));
    }
    //对象的属性和方法
    @Test
    public void test3(){
        //创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        //创建数据上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("user", new User("lisi"));
        System.out.println(expressionParser.parseExpression("#user.name").getValue(evaluationContext));
        System.out.println(expressionParser.parseExpression("#user.getName()").getValue(evaluationContext));
    }
    //数组，集合，map
    @Test
    public void test5(){
        //创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        //创建数据上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        //设置数组变量
        evaluationContext.setVariable("users", new User[]{new User("Tom")});
        //设置集合变量
        evaluationContext.setVariable("userList", Collections.singletonList(new User("Mary")));
        //设置map变量
        evaluationContext.setVariable("userMap", Collections.singletonMap("u123", new User("u123")));
        System.out.println(expressionParser.parseExpression("#users[0].name").getValue(evaluationContext));
        System.out.println(expressionParser.parseExpression("#userList[0].name").getValue(evaluationContext));
        System.out.println(expressionParser.parseExpression("#userMap['u123'].name").getValue(evaluationContext));
    }
    //普通方法调用
    @Test
    public void test6(){
        //创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        System.out.println(expressionParser.parseExpression("'hello'.substring(2)").getValue());
    }
    //操作符
    @Test
    public void test7(){
        //创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        System.out.println(expressionParser.parseExpression("1 < 4").getValue());
        System.out.println(expressionParser.parseExpression("1 < 4 and 5 > 9 ").getValue());
        System.out.println(expressionParser.parseExpression("1 + 3 - 5").getValue());
    }
    //引用IOC容器中的bean
    @Test
    public void test8(){
        //创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        //创建数据上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        //创建IOC容器上下文
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        //创建bean表达式上下文
        BeanExpressionContext beanExpressionContext = new BeanExpressionContext((ConfigurableBeanFactory) context.getAutowireCapableBeanFactory(), null);
        evaluationContext.setRootObject(beanExpressionContext);
        //添加属性访问器 从IOC容器中获取bean
        evaluationContext.addPropertyAccessor(new BeanExpressionContextAccessor());
        System.out.println(expressionParser.parseExpression("#{user.name}", new TemplateParserContext()).getValue(evaluationContext));
    }
    //默认支持#{}的格式来引用bean
    @Test
    public void test9(){
        //创建表达式解析器
        ExpressionParser expressionParser = new SpelExpressionParser();
        //创建数据上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();
        //创建IOC容器上下文
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        //创建bean表达式上下文
        BeanExpressionContext beanExpressionContext = new BeanExpressionContext((ConfigurableBeanFactory) context.getAutowireCapableBeanFactory(), null);
        evaluationContext.setRootObject(beanExpressionContext);
        //添加属性访问器 从IOC容器中获取bean
        evaluationContext.addPropertyAccessor(new BeanExpressionContextAccessor());
        System.out.println(expressionParser.parseExpression("#{user.name}", new TemplateParserContext()).getValue(evaluationContext));
    }

}
