package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brownfield.pss.fares.apigateway.FaresApiGateway;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FaresApiGateway.class)
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
