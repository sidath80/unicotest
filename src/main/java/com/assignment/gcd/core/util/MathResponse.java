package com.assignment.gcd.core.util;

import java.io.Serializable;

public class MathResponse implements Serializable{
	
	private static final long serialVersionUID = 3891560955147512715L;
	private String id;
	private int number;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
