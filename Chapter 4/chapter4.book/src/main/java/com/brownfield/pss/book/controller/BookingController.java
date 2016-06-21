package com.brownfield.pss.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.pss.book.component.BookingComponent;
import com.brownfield.pss.book.entity.BookingRecord;

@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
	BookingComponent bookingComponent;
	
	@Autowired
	BookingController(BookingComponent bookingComponent){
		this.bookingComponent = bookingComponent;
	}

	@RequestMapping(value="/create" , method = RequestMethod.POST)
	long book(@RequestBody BookingRecord record){
		System.out.println("Booking Request" + record); 
		return bookingComponent.book(record);
	}
	
	@RequestMapping("/get/{id}")
	BookingRecord getBooking(@PathVariable long id){
		return bookingComponent.getBooking(id);
	}	
}
