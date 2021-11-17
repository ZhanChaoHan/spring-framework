package com.jachs.aspectj.part2;

/***
 * 
 * @author zhanchaohan
 *
 */
public class HumanTest {
	public static void main(String[] args) {
		Human human=new Human("tom");
		
//		human.breakFast();
		human.breakFast(true);
	}
}
