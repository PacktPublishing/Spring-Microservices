package org.rvslab.chapter2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rvslab.chapter2.Application;
import org.rvslab.chapter2.Greet;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
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

	@Test
	public void testSecureService() {	
		String plainCreds = "guest:guest123";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encode(plainCreds.getBytes())));
		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Greet> response = restTemplate.exchange("http://localhost:8080", HttpMethod.GET, request, Greet.class);
		Assert.assertEquals("Hello World!", response.getBody().getMessage());
	}
	

	@Test
	public void testOAuthService() {	
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setUsername("guest");
        resource.setPassword("guest123");
        resource.setAccessTokenUri("http://localhost:8080/oauth/token");
        resource.setClientId("trustedclient");
        resource.setClientSecret("trustedclient123");
        resource.setGrantType("password");
  
        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);
 
        Greet greet = restTemplate.getForObject("http://localhost:8080", Greet.class);

        Assert.assertEquals("Hello World!", greet.getMessage());
	}
	
	
}
