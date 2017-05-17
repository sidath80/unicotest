package com.assignment.gcd.core.jpa.domain;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class IntNumber extends AbstractEntity{
	
	@JsonProperty(required = true)
    @ApiModelProperty(notes = "Number", required = true)
	private int number;
	
	public IntNumber(){
    }
	
    public IntNumber(int i){
    	this.number=i;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
