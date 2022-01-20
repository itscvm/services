package com.shivam.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger log=LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	@Retry(name="sample-api",fallbackMethod = "hardCodedResponse")
	public String sampleApi() {
		
		  log.info("call recieved");
		  
		  ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/sampleapi", String.class);
		  return entity.getBody();
	}
	
	public String hardCodedResponse( Exception ex) {
		
		return "fallBackResponse";
	}

}
