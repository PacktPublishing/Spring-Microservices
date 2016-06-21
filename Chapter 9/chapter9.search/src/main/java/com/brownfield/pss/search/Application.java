package com.brownfield.pss.search;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.brownfield.pss.search.entity.Fares;
import com.brownfield.pss.search.entity.Flight;
import com.brownfield.pss.search.entity.Inventory;
import com.brownfield.pss.search.repository.FlightRepository;
import com.netflix.appinfo.AmazonInfo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient 
@EnableSwagger2 
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
 	
	@Autowired
	private FlightRepository flightRepository;
	
 
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... strings) throws Exception {
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight("BF100", "SEA","SFO","22-JAN-16",new Fares("100", "USD"),new Inventory(100)));
		flights.add(new Flight("BF101", "NYC","SFO","22-JAN-16",new Fares("101", "USD"),new Inventory(100)));
		flights.add(new Flight("BF105", "NYC","SFO","22-JAN-16",new Fares("105", "USD"),new Inventory(100)));
		flights.add(new Flight("BF106", "NYC","SFO","22-JAN-16",new Fares("106", "USD"),new Inventory(100)));
		flights.add(new Flight("BF102", "CHI","SFO","22-JAN-16",new Fares("102", "USD"),new Inventory(100)));
		flights.add(new Flight("BF103", "HOU","SFO","22-JAN-16",new Fares("103", "USD"),new Inventory(100)));
		flights.add(new Flight("BF104", "LAX","SFO","22-JAN-16",new Fares("104", "USD"),new Inventory(100)));
	    
		flightRepository.save(flights);
		
		logger.info("Looking to load flights...");
		for (Flight flight : flightRepository.findByOriginAndDestinationAndFlightDate("NYC", "SFO", "22-JAN-16")) {
	        logger.info(flight.toString());
	    }
	}
}
//UN-COMMENT THIS IF NOT RUNNING IN AWS
@Configuration
class EurekaConfig { 

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
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
		    
		  //  config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4)); 
		   
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
