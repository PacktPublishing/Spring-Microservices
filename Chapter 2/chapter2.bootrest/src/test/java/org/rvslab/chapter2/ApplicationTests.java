package org.rvslab.chapter2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class ApplicationTests {
@Test
	public void testVanillaService() {
		RestTemplate restTemplate = new RestTemplate();
Greet greet = restTemplate.getForObject("http://localhost:8080", Greet.class);
	    Assert.assertEquals("Hello World!", greet.getMessage());
	}
}
