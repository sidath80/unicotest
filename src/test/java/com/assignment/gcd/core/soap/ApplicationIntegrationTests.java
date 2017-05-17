package com.assignment.gcd.core.soap;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import com.assignment.gcd.core.soap.gen.GetGcdListRequest;
import com.assignment.gcd.core.soap.gen.GetGcdRequest;
import com.assignment.gcd.core.soap.gen.GetGcdSumRequest;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTests {

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @LocalServerPort
    private int port = 0;

    @Before
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetGcdRequest.class));
        marshaller.afterPropertiesSet();
    }
    
    @Test
    public void test2SendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetGcdRequest request = new GetGcdRequest();
        request.setName("test");
        assertThat(ws.marshalSendAndReceive("http://localhost:"
                + port + "/ws", request)).isNotNull();
        
    }
    
   @Test
    public void test3SendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetGcdListRequest request = new GetGcdListRequest();
        request.setName("test");
        assertThat(ws.marshalSendAndReceive("http://localhost:"
                + port + "/ws", request)).isNotNull();
        
    }
    
    @Test
    public void test4SendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetGcdSumRequest request = new GetGcdSumRequest();
        request.setName("test");
        assertThat(ws.marshalSendAndReceive("http://localhost:"
                + port + "/ws", request)).isNotNull();
        
    }
}