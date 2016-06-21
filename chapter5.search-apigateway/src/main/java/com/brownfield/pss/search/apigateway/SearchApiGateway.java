package com.brownfield.pss.search.apigateway;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@CrossOrigin
@EnableSwagger2 
public class SearchApiGateway {

	@Bean
	public CustomZuulFilter customFilter() {
		return new CustomZuulFilter();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SearchApiGateway.class, args);
	}
	
}

@RestController 
class SearchAPIGatewayController {

	@RequestMapping("/")
	String greet(HttpServletRequest req){
		return "<H1>Search Gateway Powered By Zuul</H1>"; 
	}
}

	
