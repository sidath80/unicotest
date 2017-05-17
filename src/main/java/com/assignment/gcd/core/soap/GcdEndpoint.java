package com.assignment.gcd.core.soap;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.assignment.gcd.core.jpa.domain.IntNumber;
import com.assignment.gcd.core.jpa.repository.GcdRepository;
import com.assignment.gcd.core.rest.MathRestController;
import com.assignment.gcd.core.soap.gen.GetGcdListRequest;
import com.assignment.gcd.core.soap.gen.GetGcdRequest;
import com.assignment.gcd.core.soap.gen.GetGcdResponse;
import com.assignment.gcd.core.soap.gen.GetGcdSumRequest;
import com.assignment.gcd.core.soap.gen.GetGcdSumResponse;


@Endpoint
public class GcdEndpoint {
	
	private static final String NAMESPACE_URI = "gen.soap.core.gcd.assignment.com";
	
	private static final Logger logger = Logger.getLogger(GcdEndpoint.class);

	@Autowired
	private GcdDataRepository dataService;
	
	@Autowired
	private GcdRepository gcdRepository;
	
	/*@Autowired
	public GcdEndpoint(GcdRepository countryRepository) {
		this.countryRepository = countryRepository;
	}*/
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcdRequest")
	@ResponsePayload
	public GetGcdResponse getGcd(@RequestPayload GetGcdRequest request) {
		GetGcdResponse response = new GetGcdResponse();
		response.setGcd(dataService.getGcd());

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcdListRequest")
	@ResponsePayload
	public GetGcdResponse getGcdList(@RequestPayload GetGcdListRequest request) {
		GetGcdResponse response = new GetGcdResponse();
		response.setGcd(dataService.getGcd());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGcdSumRequest")
	@ResponsePayload
	public GetGcdSumResponse getGcdSum(@RequestPayload GetGcdSumRequest request) {
		
        logger.info("getGcdSum");
        int gcd=0;
        GetGcdSumResponse response = new GetGcdSumResponse();
		List<IntNumber> numbers=null;
		try {
			
			numbers = gcdRepository.findAll();
			int[] myIntArray = new int[numbers.size()];
			for(int i=0;i<numbers.size();i++){
				myIntArray[i]=numbers.get(i).getNumber();
			}
			gcd=dataService.findGcd(myIntArray);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setGcd(gcd);

		return response;
	}
	
	


}
