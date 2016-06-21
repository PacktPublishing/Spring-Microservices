package org.rvslab.chapter2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/")
	public Greet sayHello(){
		return new Greet("Hello World!");
	}
}
class Greet { 
	private String message;
	public Greet(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
   
}
