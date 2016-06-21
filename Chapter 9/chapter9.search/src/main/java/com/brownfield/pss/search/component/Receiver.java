package com.brownfield.pss.search.component;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(SearchSink.class)
public class Receiver {
	
	@Autowired
	SearchComponent searchComponent;
 	
	public Receiver(){
		
	}
	/**	

	public Receiver(SearchComponent searchComponent){
		this.searchComponent = searchComponent;
	}
	@Bean
	Queue queue() {
		return new Queue("InventoryQ", false);
	}
	
	@RabbitListener(queues = "InventoryQ")
    public void processMessage(Map<String,Object> fare) {
       searchComponent.updateInventory((String)fare.get("FLIGHT_NUMBER"),(String)fare.get("FLIGHT_DATE"),(int)fare.get("NEW_INVENTORY"));
       //call repository and update the fare for the given flight
    }	**/
	
		@ServiceActivator(inputChannel = SearchSink.INVENTORYQ)
		public void accept(Map<String,Object> fare){
		       searchComponent.updateInventory((String)fare.get("FLIGHT_NUMBER"),(String)fare.get("FLIGHT_DATE"),(int)fare.get("NEW_INVENTORY"));
		}	
}

interface SearchSink {
	  public static String INVENTORYQ="inventoryQ"; 
	  @Input("inventoryQ")
	  public MessageChannel inventoryQ();
  
}

	