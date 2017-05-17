package com.assignment.gcd.core.rest;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;

import com.assignment.gcd.core.GcdCoreApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GcdCoreApplication.class)
@SpringBootTest
public class TestMathRestController {
	
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/list").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is(200))
		.andDo(print());
	}
	
	@Test
	public void verifyPush() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/push")
	    //.contentType(MediaType.)
	    // .content("{\"text\" : \"New ToDo Sample\", \"completed\" : \"false\" }")	
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is(200))
		.andDo(print());
	}
	
	

}
