package com.shivam.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieverExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		CurrencyExchange currencyExchange=repository.findByFromAndTo(from, to);
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		
		return currencyExchange;
	}
	@PostMapping("/currency-exchange/save")
	public CurrencyExchange saveValues(@ModelAttribute CurrencyExchange reqstBody) {
		
		reqstBody.setEnvironment(environment.getProperty("local.server.port"));
		
		CurrencyExchange exchange=repository.save(reqstBody);
		
		return exchange;
	}

}
