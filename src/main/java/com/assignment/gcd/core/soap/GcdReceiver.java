package com.assignment.gcd.core.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.assignment.gcd.core.jpa.domain.IntNumber;

@Component
public class GcdReceiver {

	@Autowired
	ConfigurableApplicationContext context;

	@JmsListener(destination = "q",containerFactory = "myFactory")
	public void receiveMessage(IntNumber number) {
		System.out.println("Q Received <" + number.getId() + ">");
	}

}
