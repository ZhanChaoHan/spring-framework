package com.jachs.spring_mail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

/***
 * 
 * @author zhanchaohan
 *
 */
public class SendTest {
	
	@Test
	public void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mail.xml");

		JavaMailSender mailSender=ctx.getBean("javaMailSender",JavaMailSender.class);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("zhanchaohan22@163.com");
		message.setTo("799516423@qq.com");
		message.setSubject("主题");
		message.setText("类容");
		
		mailSender.send(message);
	}
	@Test
	public void test2() throws Exception{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mail.xml");

		JavaMailSender mailSender=ctx.getBean("javaMailSender",JavaMailSender.class);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom("zhanchaohan22@163.com");
		helper.setTo("799516423@qq.com");
		helper.setSubject("主题：发送有附件的邮件");
		helper.setText("你好，我是测试，我正在测试发送一封有附件的邮件。");


		FileSystemResource file1 = new FileSystemResource(new File("f:\\assss.txt"));
		FileSystemResource file2 = new FileSystemResource(new File("f:\\cc.txt"));

		helper.addAttachment("a.txt", file1);
		helper.addAttachment("b.txt", file2);

		mailSender.send(mimeMessage);
	}
	@Test
	public void test3() throws Exception{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mail.xml");

		JavaMailSender mailSender=ctx.getBean("javaMailSender",JavaMailSender.class);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom("zhanchaohan22@163.com");
		helper.setTo("799516423@qq.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:hello\" ></body></html>", true);


		// 注意addInline()中资源名称 hello 必须与 text正文中cid:hello对应起来
		FileSystemResource file1 = new FileSystemResource(new File("f:\\e.png"));
		helper.addInline("hello", file1);

		mailSender.send(mimeMessage);
	}
}
