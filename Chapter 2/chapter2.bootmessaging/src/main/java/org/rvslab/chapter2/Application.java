package org.rvslab.chapter2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication	
public class Application implements CommandLineRunner{

	@Autowired
	Sender sender;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
    	sender.send("Hello Messaging..!!!");
    }
}

@Component 
class Sender {
	
	RabbitMessagingTemplate template;

	@Autowired
	Sender(RabbitMessagingTemplate template){
		this.template = template;
	}
	@Bean
	Queue queue() {
		return new Queue("TestQ", false);
	}
	
	public void send(String message){
		template.convertAndSend("TestQ", message);
	}
}

@Component
class Receiver {
	@RabbitListener(queues = "TestQ")
    public void processMessage(String content) {
       System.out.println(content);
    }
	
}

