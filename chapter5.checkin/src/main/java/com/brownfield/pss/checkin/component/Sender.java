package com.brownfield.pss.checkin.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@EnableBinding(CheckInSource.class)
@Component 
public class Sender {
	
	public Sender(){
		
	}
	
	/**
	 * RabbitMessagingTemplate template;
	 
	@Autowired
	Sender(RabbitMessagingTemplate template){
		this.template = template;
	}
	@Bean
	Queue queue() {
		return new Queue("CheckInQ", false);
	}
	
	public void send(Object message){
		template.convertAndSend("CheckInQ", message);
	}
**/
	
	@Output (CheckInSource.CHECKINQ)
	@Autowired
	private MessageChannel messageChannel;	
	
	public void send(Object message){
		//template.convertAndSend("InventoryQ", message);
		messageChannel.send(MessageBuilder.withPayload(message).build());
	}
	}
	
	interface CheckInSource {
	  public static String CHECKINQ="checkInQ"; 
	  @Output("checkInQ")
	  public MessageChannel checkInQ();
	  	  
	}