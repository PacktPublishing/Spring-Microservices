package com.brownfield.pss.search.apigateway;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@CrossOrigin
@EnableSwagger2 
@EnableCircuitBreaker
public class SearchApiGateway {

	private static final Logger logger = LoggerFactory.getLogger(SearchApiGateway.class);
	
	@Bean
	  public AlwaysSampler defaultSampler() {
	    return new AlwaysSampler();
	}
	
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
	private static final Logger logger = LoggerFactory.getLogger(SearchAPIGatewayController.class);

	@Autowired
	SearchAPIGatewayComponent component;

	@RequestMapping("/")
	String greet(HttpServletRequest req){
		return "<H1>Search Gateway Powered By Zuul</H1>"; 
	}

	@RequestMapping("/hubongw")
	String getHub(){
		logger.info("Search Request in API gateway for getting Hub, forwarding to search-service ");
		return component.getHub(); 
	} 
}

@Component	
class SearchAPIGatewayComponent { 
 	@Autowired 
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getDefaultHub")
	public String getHub(){
		String hub = restTemplate.getForObject("http://search-service/search/hub", String.class);
		return hub;
	}
	public String getDefaultHub(){
		return "Possibily SFO";
	}
}

@Configuration
class AppConfiguration {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}