package com.assignment.gcd.core.rest;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.gcd.core.jpa.domain.IntNumber;
import com.assignment.gcd.core.jpa.repository.GcdRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class MathRestController {

	private static final Logger logger = Logger.getLogger(MathRestController.class);

	@Autowired
	private GcdRepository numberRepository;
	
	@Autowired
	private ApplicationContext context;

	@ApiOperation(value = "Add two integers", nickname = "Add two integers")
	@RequestMapping(value = "/push", method = RequestMethod.POST)
	public ResponseEntity<String> push(@RequestParam(value = "i", defaultValue = "0") int i,
			@RequestParam(value = "j", defaultValue = "0") int j) {
		
		logger.info("push i & j " + i + " : " + j);
		try {
			IntNumber number = new IntNumber(i);
			IntNumber number2 = new IntNumber(j);
			
			
			JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

	        // Send a message with a POJO - the template reuse the message converter
	        System.out.println("Sending an email message.");
	       
	         number=numberRepository.save(number);
	         number2=numberRepository.save(number2);
			
			 jmsTemplate.convertAndSend("q", number);
		     jmsTemplate.convertAndSend("q", number2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED + "", HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<String>(HttpStatus.OK + "", HttpStatus.OK);
	}

	@ApiOperation(value = "List integers", nickname = "List integers")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<IntNumber>> list() {
		
		logger.info("list");
		
		List<IntNumber> numbers=null;
		try {
			numbers = numberRepository.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<IntNumber>>(numbers, HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<List<IntNumber>>(numbers, HttpStatus.OK);
	}
}
