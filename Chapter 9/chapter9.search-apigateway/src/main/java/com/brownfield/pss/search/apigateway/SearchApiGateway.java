package com.brownfield.pss.search.apigateway;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.netflix.appinfo.AmazonInfo;

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

	

@Configuration
class EurekaConfig { 

	private static final Logger logger = LoggerFactory.getLogger(EurekaConfig.class);
	
    @Bean
    public EurekaInstanceConfigBean eurekaInstanceConfigBean() {
    	EurekaInstanceConfigBean config = new EurekaInstanceConfigBean();
    	try { 
	   		logger.info("Ereka Pre Configuring-3");
		   AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		    config.setDataCenterInfo(info);
		    info.getMetadata().put(AmazonInfo.MetaDataKey.publicHostname.getName(), info.get(AmazonInfo.MetaDataKey.publicIpv4));
		    config.setHostname(info.get(AmazonInfo.MetaDataKey.localHostname));
		    
		    logger.info("hostname" + info.get(AmazonInfo.MetaDataKey.localHostname));
		    logger.info("IP" + info.get(AmazonInfo.MetaDataKey.publicIpv4));
		    
		//    config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4)); 
		   
	   		config.setNonSecurePortEnabled(true);
	        config.setNonSecurePort(0); //change this later
	     //   config.setPreferIpAddress(true);
	        
	       // config.setIpAddress("54.85.107.37");
	        config.getMetadataMap().put("instanceId",  info.get(AmazonInfo.MetaDataKey.localHostname));
	 
		   // logger.info("info" + info); 
    	}catch (Exception e){
    		e.printStackTrace();
    	}
	    return config;
	}
}
