package com.jachs.aspectj.demo.part1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jachs.aspectj.demo.HuMan;

/***
 * 
 * @author zhanchaohan
 *
 */
@SpringBootTest()
public class HuManAspTest {
	@Autowired
	private HuMan huMan;
	
	@Test
	public void test() {
		try {
			huMan.speak();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
