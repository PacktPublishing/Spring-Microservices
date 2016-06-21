package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
 
public class Swagger2SpringBoot {

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(Swagger2SpringBoot.class, args);
  }
}

@RestController 
class GreetController {
	
	@RequestMapping("/")
	String greet(){
		return "Hello World!!";
	}	
}