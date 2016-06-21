package com.brownfield.pss.book.component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.brownfield.pss.book.entity.BookingRecord;
import com.brownfield.pss.book.entity.Inventory;
import com.brownfield.pss.book.entity.Passenger;
import com.brownfield.pss.book.repository.BookingRepository;
import com.brownfield.pss.book.repository.InventoryRepository;

@Component
public class BookingComponent {
	private static final Logger logger = LoggerFactory.getLogger(BookingComponent.class);
	private static final String FareURL = "http://localhost:8080/fares";
	
	BookingRepository bookingRepository;
	InventoryRepository inventoryRepository;
	
	//@Autowired
	private RestTemplate restTemplate;
	
	Sender sender;

	@Autowired
	public BookingComponent (BookingRepository bookingRepository,
					  Sender sender,InventoryRepository inventoryRepository){
		this.bookingRepository = bookingRepository;
		this.restTemplate = new RestTemplate();
		this.sender = sender;
		this.inventoryRepository = inventoryRepository;
	}
	public long book(BookingRecord record) {
		logger.info("calling fares to get fare");
		//call fares to get fare
		Fare fare = restTemplate.getForObject(FareURL +"/get?flightNumber="+record.getFlightNumber()+"&flightDate="+record.getFlightDate(),Fare.class);
		logger.info("calling fares to get fare "+ fare);
		//check fare
		if (!record.getFare().equals(fare.getFare()))
			throw new BookingException("fare is tampered");
		logger.info("calling inventory to get inventory");
		//check inventory
		Inventory inventory = inventoryRepository.findByFlightNumberAndFlightDate(record.getFlightNumber(),record.getFlightDate());
		if(!inventory.isAvailable(record.getPassengers().size())){
			throw new BookingException("No more seats avaialble");
		}
		logger.info("successfully checked inventory" + inventory);
		logger.info("calling inventory to update inventory");
		//update inventory
		inventory.setAvailable(inventory.getAvailable() - record.getPassengers().size());
		inventoryRepository.saveAndFlush(inventory);
		logger.info("sucessfully updated inventory");
		//save booking
		record.setStatus(BookingStatus.BOOKING_CONFIRMED); 
		Set<Passenger> passengers = record.getPassengers();
		passengers.forEach(passenger -> passenger.setBookingRecord(record));
		record.setBookingDate(new Date());
		long id=  bookingRepository.save(record).getId();
		logger.info("Successfully saved booking");
		//send a message to search to update inventory
		logger.info("sending a booking event");
		Map<String, Object> bookingDetails = new HashMap<String, Object>();
		bookingDetails.put("FLIGHT_NUMBER", record.getFlightNumber());
		bookingDetails.put("FLIGHT_DATE", record.getFlightDate());
		bookingDetails.put("NEW_INVENTORY", inventory.getBookableInventory());
		sender.send(bookingDetails);
		logger.info("booking event successfully delivered "+ bookingDetails);
		return id;
	}

	public BookingRecord getBooking(long id) {
		return bookingRepository.findOne(id);
	}
	
	
	public void updateStatus(String status, long bookingId) {
		BookingRecord record = bookingRepository.findOne(bookingId);
		record.setStatus(status);
	}
	
}
