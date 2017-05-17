package com.assignment.gcd.core.jpa.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class AbstractEntity
{

	@Id @GeneratedValue(generator = "system-uuid") @GenericGenerator(name = "system-uuid", strategy = "uuid") 
	private String id;

	@Override
	public int hashCode()
	{
		return id == null ? 0 : id.hashCode();
	}

	public String getId()
	{
		return id;
	}

}
