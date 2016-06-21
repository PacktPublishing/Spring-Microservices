package com.brownfied.pss.lcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.brownfied.pss.lcm.metricscollector.MetricsCollector;
@EnableDiscoveryClient
@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	MetricsCollector metricsCollector;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		metricsCollector.start();
	}
}