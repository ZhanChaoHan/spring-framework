package com.jachs.spring_jdbc.entity;

import java.util.Date;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Computer {
	private String computerId;
	private String computerName;
	private long computerPrice;
	private Date madeTime;
	
	public Computer() {
		super();
	}
	public Computer(String computerId, String computerName, long computerPrice, Date madeTime) {
		super();
		this.computerId = computerId;
		this.computerName = computerName;
		this.computerPrice = computerPrice;
		this.madeTime = madeTime;
	}
	public String getComputerId() {
		return computerId;
	}
	public void setComputerId(String computerId) {
		this.computerId = computerId;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public long getComputerPrice() {
		return computerPrice;
	}
	public void setComputerPrice(long computerPrice) {
		this.computerPrice = computerPrice;
	}
	public Date getMadeTime() {
		return madeTime;
	}
	public void setMadeTime(Date madeTime) {
		this.madeTime = madeTime;
	}
	
	
	
}
